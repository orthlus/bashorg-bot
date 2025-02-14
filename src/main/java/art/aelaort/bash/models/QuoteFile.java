package art.aelaort.bash.models;

import java.time.LocalDate;

public record QuoteFile(
		Integer quoteId,
		String quote,
		String fileId,
		String fileUrlId,
		Integer quoteOriginalId,
		LocalDate quoteDate,
		Integer quoteRating,
		Boolean thumbFileExists
) {
}
