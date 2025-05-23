package art.aelaort.bash;

import lombok.RequiredArgsConstructor;
import art.aelaort.bash.models.Quote;
import art.aelaort.bash.models.QuoteFile;
import art.aelaort.bash.models.QuoteFileId;
import art.aelaort.bash.models.QuoteFileUrlId;
import art.aelaort.tables.Quotes;
import art.aelaort.tables.records.QuotesRecord;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static org.jooq.Records.mapping;

@Component
@RequiredArgsConstructor
public class BashRepo {
	private final DSLContext dsl;
	private final Quotes quotes = Quotes.QUOTES;

	public void setThumbFileExistsByQuoteIds(List<Integer> quoteIds, boolean value) {
		dsl.update(quotes)
				.set(quotes.THUMB_FILE_EXISTS, value)
				.where(quotes.ID.in(quoteIds))
				.execute();
	}

	public Set<QuoteFile> getQuotesWithFalseThumbFileExistsTopN(int n) {
		return dsl.select(quotes.ID,
						quotes.QUOTE,
						quotes.TELEGRAM_FILE_ID,
						quotes.FILE_URL_ID,
						quotes.QUOTE_ID,
						quotes.QUOTE_DATE,
						quotes.RATING,
						quotes.THUMB_FILE_EXISTS)
				.from(quotes)
				.where(quotes.THUMB_FILE_EXISTS.eq(false))
				.orderBy(quotes.ID)
				.limit(n)
				.fetchSet(mapping(QuoteFile::new));
	}

	public int notExistsThumbFileUrlIdCount() {
		return dsl.fetchCount(dsl.selectFrom(quotes)
				.where(quotes.THUMB_FILE_EXISTS.eq(false)));
	}

	public void addFileUrlIds(List<QuoteFileUrlId> quoteFileUrlIds) {
		dsl.transaction(trx -> {
			for (QuoteFileUrlId quoteFileUrlId : quoteFileUrlIds) {
				trx.dsl().update(quotes)
						.set(quotes.FILE_URL_ID, quoteFileUrlId.fileUrlId())
						.where(quotes.ID.eq(quoteFileUrlId.quoteId()))
						.execute();
			}
		});
	}

	public void addFileUrlId(Integer quoteId, String fileUrlId) {
		dsl.update(quotes)
				.set(quotes.FILE_URL_ID, fileUrlId)
				.where(quotes.ID.eq(quoteId))
				.execute();
	}

	public Set<QuoteFile> getQuotesWithNullFileUrlTopN(int n) {
		return dsl.select(quotes.ID,
						quotes.QUOTE,
						quotes.TELEGRAM_FILE_ID,
						quotes.FILE_URL_ID,
						quotes.QUOTE_ID,
						quotes.QUOTE_DATE,
						quotes.RATING,
						quotes.THUMB_FILE_EXISTS)
				.from(quotes)
				.where(quotes.FILE_URL_ID.isNull())
				.orderBy(quotes.ID)
				.limit(n)
				.fetchSet(mapping(QuoteFile::new));
	}

	public int hasNoFileUrlIdCount() {
		return dsl.fetchCount(dsl.selectFrom(quotes)
				.where(quotes.FILE_URL_ID.isNull()));
	}

	public Set<QuoteFile> getQuotesWithNullFileIdTopN(int n) {
		return dsl.select(quotes.ID,
						quotes.QUOTE,
						quotes.TELEGRAM_FILE_ID,
						quotes.FILE_URL_ID,
						quotes.QUOTE_ID,
						quotes.QUOTE_DATE,
						quotes.RATING,
						quotes.THUMB_FILE_EXISTS)
				.from(quotes)
				.where(quotes.TELEGRAM_FILE_ID.isNull())
				.orderBy(quotes.ID)
				.limit(n)
				.fetchSet(mapping(QuoteFile::new));
	}

	public int hasNoFileIdCount() {
		return dsl.fetchCount(dsl.selectFrom(quotes)
				.where(quotes.TELEGRAM_FILE_ID.isNull()));
	}

	public void addFileIds(List<QuoteFileId> quoteFileIds) {
		dsl.transaction(trx -> {
			for (QuoteFileId quoteFileId : quoteFileIds) {
				trx.dsl().update(quotes)
						.set(quotes.TELEGRAM_FILE_ID, quoteFileId.fileId())
						.where(quotes.ID.eq(quoteFileId.quoteId()))
						.execute();
			}
		});
	}

	public void addFileId(Integer quoteId, String fileId) {
		dsl.update(quotes)
				.set(quotes.TELEGRAM_FILE_ID, fileId)
				.where(quotes.ID.eq(quoteId))
				.execute();
	}

	public Set<QuoteFile> search(String query) {
		return dsl.select(quotes.ID,
						quotes.QUOTE,
						quotes.TELEGRAM_FILE_ID,
						quotes.FILE_URL_ID,
						quotes.QUOTE_ID,
						quotes.QUOTE_DATE,
						quotes.RATING,
						quotes.THUMB_FILE_EXISTS)
				.from(quotes)
				.where("{0} %> {1}", quotes.QUOTE, query)
				.orderBy(quotes.RATING.desc())
				.limit(10)
				.fetchSet(mapping(QuoteFile::new));
	}

	public boolean dataExists() {
		return dsl.fetchExists(quotes);
	}

	public void saveQuotes(List<Quote> quotesList) {
		dsl.batchInsert(quotesList.stream()
						.map(quote -> new QuotesRecord(null,
								quote.getRating(),
								quote.getText(),
								null,
								null,
								quote.getId(),
								quote.getDate(),
								null))
						.toList())
				.execute();
	}

	public QuoteFile getById(int id) {
		return dsl.select(quotes.ID,
						quotes.QUOTE,
						quotes.TELEGRAM_FILE_ID,
						quotes.FILE_URL_ID,
						quotes.QUOTE_ID,
						quotes.QUOTE_DATE,
						quotes.RATING,
						quotes.THUMB_FILE_EXISTS)
				.from(quotes)
				.orderBy(quotes.ID)
				.limit(DSL.inline(1))
				.offset(DSL.inline(id - 1))
				.fetchOne(mapping(QuoteFile::new));
	}

	public int getCount() {
		return dsl.fetchCount(quotes);
	}
}
