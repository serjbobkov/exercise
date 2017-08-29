package ru.bsa.tinkoff;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.annotation.Nonnull;
import javax.sql.DataSource;
import java.sql.Connection;

public final class TestUtil {
    public static DataSource currentTransactionDataSource(@Nonnull final DataSource dataSource) {
        final Connection connection = DataSourceUtils.getConnection(dataSource);
        return new SingleConnectionDataSource(connection, true);
    }
}
