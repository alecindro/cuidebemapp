package br.com.cuidebemapp.uaa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cuidebemapp.config.ApplicationProperties;
import br.com.cuidebemapp.config.liquibase.LiquibaseMultiTenantcy;
import br.com.cuidebemapp.service.dto.AdminDTO;
import br.com.cuidebemapp.service.dto.ResidenciaDTO;
import br.com.cuidebemapp.uaa.model.Authority;
import br.com.cuidebemapp.uaa.model.Residencia;
import br.com.cuidebemapp.uaa.repository.AuthorityRepository;
import br.com.cuidebemapp.uaa.repository.ResidenciaRepository;

@Service
@Transactional(transactionManager = "transactionManagerUAA")
public class ResidenciaService {

	private final Logger log = LoggerFactory.getLogger(ResidenciaService.class);
	@Autowired
	private LiquibaseMultiTenantcy springLiquibase;
	@Autowired
	private ApplicationProperties appProperties;
	@Autowired
	private ResidenciaRepository residenciaRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthorityRepository authorityRepository;



	private String createSchema(String posFixTenant) throws Exception {
		String schema = null;
		schema = springLiquibase.createSchema(appProperties.getTenantPrefix(), posFixTenant);
		return schema;
	}

	public ResidenciaDTO create(ResidenciaDTO residenciaDTO) throws Exception {
		if (residenciaDTO.getResidencia().getIdresidencia() != null) {
			throw new Exception("Residencia já existe");
		}
		String schema = null;
		try {
			Residencia residencia = residenciaRepository.save(residenciaDTO.getResidencia());
			schema = createSchema(String.valueOf(residencia.getIdresidencia()));
			Authority authority = new Authority();
			authority.setName(schema);
			authorityRepository.save(authority);
			AdminDTO adminDTO = residenciaDTO.getAdminDTO();
			userService.createAdminUser(adminDTO, schema);
			residenciaDTO.setResidencia(residencia);
		} catch (Exception e) {
			if (schema != null) {
				springLiquibase.dropSchema(schema);
			}
			
		}
		return residenciaDTO;

	}

	
}
