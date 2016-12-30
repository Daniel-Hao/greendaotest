package com.daniel.www.greendao.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.daniel.www.greendao.model.GDUserInfoMore;

import com.daniel.www.greendao.model.GDUserLanguages;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table GDUSER_LANGUAGES.
*/
public class GDUserLanguagesDao extends AbstractDao<GDUserLanguages, Long> {

    public static final String TABLENAME = "GDUSER_LANGUAGES";

    /**
     * Properties of entity GDUserLanguages.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property User_id = new Property(1, String.class, "user_id", false, "USER_ID");
        public final static Property Language_code = new Property(2, String.class, "language_code", false, "LANGUAGE_CODE");
        public final static Property User_info_id = new Property(3, long.class, "user_info_id", false, "USER_INFO_ID");
    };

    private DaoSession daoSession;

    private Query<GDUserLanguages> gDUserInfoMore_Rp_UserInfoToLanguagesQuery;

    public GDUserLanguagesDao(DaoConfig config) {
        super(config);
    }
    
    public GDUserLanguagesDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'GDUSER_LANGUAGES' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'USER_ID' TEXT," + // 1: user_id
                "'LANGUAGE_CODE' TEXT," + // 2: language_code
                "'USER_INFO_ID' INTEGER NOT NULL );"); // 3: user_info_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'GDUSER_LANGUAGES'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, GDUserLanguages entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindString(2, user_id);
        }
 
        String language_code = entity.getLanguage_code();
        if (language_code != null) {
            stmt.bindString(3, language_code);
        }
        stmt.bindLong(4, entity.getUser_info_id());
    }

    @Override
    protected void attachEntity(GDUserLanguages entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public GDUserLanguages readEntity(Cursor cursor, int offset) {
        GDUserLanguages entity = new GDUserLanguages( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // user_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // language_code
            cursor.getLong(offset + 3) // user_info_id
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, GDUserLanguages entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUser_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLanguage_code(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUser_info_id(cursor.getLong(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(GDUserLanguages entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(GDUserLanguages entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "rp_UserInfoToLanguages" to-many relationship of GDUserInfoMore. */
    public List<GDUserLanguages> _queryGDUserInfoMore_Rp_UserInfoToLanguages(long user_info_id) {
        synchronized (this) {
            if (gDUserInfoMore_Rp_UserInfoToLanguagesQuery == null) {
                QueryBuilder<GDUserLanguages> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.User_info_id.eq(null));
                gDUserInfoMore_Rp_UserInfoToLanguagesQuery = queryBuilder.build();
            }
        }
        Query<GDUserLanguages> query = gDUserInfoMore_Rp_UserInfoToLanguagesQuery.forCurrentThread();
        query.setParameter(0, user_info_id);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getGDUserInfoMoreDao().getAllColumns());
            builder.append(" FROM GDUSER_LANGUAGES T");
            builder.append(" LEFT JOIN GDUSER_INFO_MORE T0 ON T.'USER_INFO_ID'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected GDUserLanguages loadCurrentDeep(Cursor cursor, boolean lock) {
        GDUserLanguages entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        GDUserInfoMore gDUserInfoMore = loadCurrentOther(daoSession.getGDUserInfoMoreDao(), cursor, offset);
         if(gDUserInfoMore != null) {
            entity.setGDUserInfoMore(gDUserInfoMore);
        }

        return entity;    
    }

    public GDUserLanguages loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<GDUserLanguages> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<GDUserLanguages> list = new ArrayList<GDUserLanguages>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<GDUserLanguages> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<GDUserLanguages> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}