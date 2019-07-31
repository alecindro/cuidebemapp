package br.com.cuidebemapp.config.liquibase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

public class LiquibaseMultiTenantcy extends SpringLiquibase {

	protected Set<String> schemas;

	@Override
	public void afterPropertiesSet() throws LiquibaseException {
		String defaultSchema = getDefaultSchema();
		if (schemas != null) {
			for (String schema : schemas) {
				setDefaultSchema(schema);
				super.afterPropertiesSet();
			}
		}
		setDefaultSchema(defaultSchema);
	}

	public void addSchema(String schema) {
		if (this.schemas == null) {
			this.schemas = new HashSet<>();
		}
		this.schemas.add(schema);
	}

	public String createSchema(String prefix, String projeto) throws Exception {
		String defaultSchema = getDefaultSchema();
		String schema = prefix.toLowerCase() + "_" + projeto.toLowerCase();
		verifySchema(getDataSource(), schema);
		addSchema(schema);
		afterPropertiesSet();
		setDefaultSchema(defaultSchema);
		return schema;
	}

	public void dropSchema(String schema) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			String sql = "DROP schema " + schema + " CASCADE";
			ps = conn.prepareStatement(sql);
			ps.execute();
			if (!conn.getAutoCommit()) {
				conn.commit();
			}
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	private void verifySchema(DataSource dataSource, String schema) throws Exception {
		String sql = "SELECT schema_name FROM information_schema.schemata WHERE schema_name = ?";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, schema);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				sql = "CREATE schema " + schema;
				ps = conn.prepareStatement(sql);
				ps.execute();
				if (!conn.getAutoCommit()) {
					conn.commit();
				}
			}
			ps.close();

		} catch (SQLException e) {
			throw new Exception(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
}
