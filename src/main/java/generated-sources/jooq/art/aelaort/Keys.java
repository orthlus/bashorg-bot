/*
 * This file is generated by jOOQ.
 */
package art.aelaort;


import art.aelaort.tables.Quotes;
import art.aelaort.tables.records.QuotesRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<QuotesRecord> QUOTES_PKEY = Internal.createUniqueKey(Quotes.QUOTES, DSL.name("quotes_pkey"), new TableField[] { Quotes.QUOTES.ID }, true);
}
