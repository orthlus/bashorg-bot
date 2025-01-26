/*
 * This file is generated by jOOQ.
 */
package art.aelaort.tables;


import art.aelaort.Indexes;
import art.aelaort.Keys;
import art.aelaort.Public;
import art.aelaort.tables.records.QuotesRecord;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function8;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row8;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Quotes extends TableImpl<QuotesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.quotes</code>
     */
    public static final Quotes QUOTES = new Quotes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<QuotesRecord> getRecordType() {
        return QuotesRecord.class;
    }

    /**
     * The column <code>public.quotes.id</code>.
     */
    public final TableField<QuotesRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.quotes.rating</code>.
     */
    public final TableField<QuotesRecord, Integer> RATING = createField(DSL.name("rating"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.quotes.quote</code>.
     */
    public final TableField<QuotesRecord, String> QUOTE = createField(DSL.name("quote"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.quotes.telegram_file_id</code>.
     */
    public final TableField<QuotesRecord, String> TELEGRAM_FILE_ID = createField(DSL.name("telegram_file_id"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.quotes.file_url_id</code>.
     */
    public final TableField<QuotesRecord, String> FILE_URL_ID = createField(DSL.name("file_url_id"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.quotes.quote_id</code>.
     */
    public final TableField<QuotesRecord, Integer> QUOTE_ID = createField(DSL.name("quote_id"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.quotes.quote_date</code>.
     */
    public final TableField<QuotesRecord, LocalDate> QUOTE_DATE = createField(DSL.name("quote_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.quotes.thumb_file_exists</code>.
     */
    public final TableField<QuotesRecord, Boolean> THUMB_FILE_EXISTS = createField(DSL.name("thumb_file_exists"), SQLDataType.BOOLEAN.defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)), this, "");

    private Quotes(Name alias, Table<QuotesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Quotes(Name alias, Table<QuotesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.quotes</code> table reference
     */
    public Quotes(String alias) {
        this(DSL.name(alias), QUOTES);
    }

    /**
     * Create an aliased <code>public.quotes</code> table reference
     */
    public Quotes(Name alias) {
        this(alias, QUOTES);
    }

    /**
     * Create a <code>public.quotes</code> table reference
     */
    public Quotes() {
        this(DSL.name("quotes"), null);
    }

    public <O extends Record> Quotes(Table<O> child, ForeignKey<O, QuotesRecord> key) {
        super(child, key, QUOTES);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.QUOTES_GIN_TRGM_IDX);
    }

    @Override
    public Identity<QuotesRecord, Integer> getIdentity() {
        return (Identity<QuotesRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<QuotesRecord> getPrimaryKey() {
        return Keys.QUOTES_PKEY;
    }

    @Override
    public Quotes as(String alias) {
        return new Quotes(DSL.name(alias), this);
    }

    @Override
    public Quotes as(Name alias) {
        return new Quotes(alias, this);
    }

    @Override
    public Quotes as(Table<?> alias) {
        return new Quotes(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Quotes rename(String name) {
        return new Quotes(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Quotes rename(Name name) {
        return new Quotes(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Quotes rename(Table<?> name) {
        return new Quotes(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, Integer, String, String, String, Integer, LocalDate, Boolean> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function8<? super Integer, ? super Integer, ? super String, ? super String, ? super String, ? super Integer, ? super LocalDate, ? super Boolean, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function8<? super Integer, ? super Integer, ? super String, ? super String, ? super String, ? super Integer, ? super LocalDate, ? super Boolean, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
