package com.testtask.hospitalwebapp;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class PostgreSQLEnumType extends org.hibernate.type.EnumType<Enum<?>> {

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value instanceof Enum) {
            st.setObject(index, ((Enum<?>) value).name(), Types.OTHER);
        } else {
            if (value == null) {
                st.setObject(index, null, Types.OTHER);
            } else {
                throw new IllegalStateException("value is not instance of Enum");
            }
        }
    }
}
