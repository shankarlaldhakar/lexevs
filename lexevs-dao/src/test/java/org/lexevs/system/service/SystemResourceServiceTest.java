package org.lexevs.system.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.LexGrid.LexBIG.Exceptions.LBParameterException;
import org.LexGrid.codingSchemes.CodingScheme;
import org.junit.Test;
import org.lexevs.dao.database.service.codingscheme.CodingSchemeService;
import org.lexevs.dao.test.LexEvsDbUnitTestBase;
import org.lexevs.registry.model.RegistryEntry;
import org.lexevs.registry.service.Registry;
import org.lexevs.registry.service.Registry.ResourceType;
import org.lexevs.system.service.DelegatingResourceManagingService.CodingSchemeAliasHolder;

public class SystemResourceServiceTest extends LexEvsDbUnitTestBase {

	@Resource
	private DelegatingResourceManagingService delegatingResourceManagingService;
	
	@Test
	public void testSetup(){
		assertNotNull(this.delegatingResourceManagingService);
	}
	
	@Test
	public void testGetClassLoader(){
		assertNotNull(delegatingResourceManagingService.getClassLoader());
	}
	
	@Test(expected=LBParameterException.class)
	public void testGetInternalVersionStringForTagError() throws LBParameterException{
		assertEquals(
				"expectedVersion", 
				delegatingResourceManagingService.getInternalVersionStringForTag("csName", "TEST_TAG"));
	}
	
	@Test(expected=LBParameterException.class)
	public void testGetInternalCodingSchemeNameForUserCodingSchemeNameError() throws LBParameterException{
		assertEquals(
				"csName", 
				delegatingResourceManagingService.getInternalCodingSchemeNameForUserCodingSchemeName("someCsLocalName", "version"));
	}
	
	@Test
	public void testGetInternalCodingSchemeNameForUserCodingSchemeNameByLocalName() throws Exception{
		Registry registryMock = createMock(Registry.class);
		
		RegistryEntry entry = new RegistryEntry();
		entry.setResourceUri("uri");
		entry.setResourceVersion("version");
		entry.setTag("");
		
		List<RegistryEntry> entries = new ArrayList<RegistryEntry>();
		entries.add(entry);
		
		expect(registryMock.getAllRegistryEntriesOfType(ResourceType.CODING_SCHEME)).andReturn(entries);
		replay(registryMock);
		
		DelegatingResourceManagingService service = new DelegatingResourceManagingService();
		CodingSchemeService csService = createMock(CodingSchemeService.class);
		
		CodingScheme cs = new CodingScheme();
		cs.setCodingSchemeName("csName");
		cs.setFormalName("fname");
		cs.setRepresentsVersion("version");
		cs.setCodingSchemeURI("uri");
		cs.addLocalName("someLocalName");
		
		expect(csService.getCodingSchemeByUriAndVersion("uri", "version")).andReturn(cs).once();
		replay(csService);
		
		service.setRegistry(registryMock);
		service.setCodingSchemeService(csService);
		service.afterPropertiesSet();
		
		assertEquals(
				"csName", 
				service.getInternalCodingSchemeNameForUserCodingSchemeName("someLocalName", "version"));
	}
	
	@Test
	public void testGetInternalCodingSchemeNameForUserCodingSchemeNameByUri() throws Exception{
		Registry registryMock = createMock(Registry.class);
		
		RegistryEntry entry = new RegistryEntry();
		entry.setResourceUri("uri");
		entry.setResourceVersion("version");
		entry.setTag("");
		
		List<RegistryEntry> entries = new ArrayList<RegistryEntry>();
		entries.add(entry);
		
		expect(registryMock.getAllRegistryEntriesOfType(ResourceType.CODING_SCHEME)).andReturn(entries);
		replay(registryMock);
		
		DelegatingResourceManagingService service = new DelegatingResourceManagingService();
		CodingSchemeService csService = createMock(CodingSchemeService.class);
		
		CodingScheme cs = new CodingScheme();
		cs.setCodingSchemeName("csName");
		cs.setFormalName("fname");
		cs.setRepresentsVersion("version");
		cs.setCodingSchemeURI("uri");
		cs.addLocalName("someLocalName");
		
		expect(csService.getCodingSchemeByUriAndVersion("uri", "version")).andReturn(cs).once();
		replay(csService);
		
		service.setRegistry(registryMock);
		service.setCodingSchemeService(csService);
		service.afterPropertiesSet();
		
		assertEquals(
				"csName", 
				service.getInternalCodingSchemeNameForUserCodingSchemeName("uri", "version"));
	}
	
	@Test
	public void testGetInternalCodingSchemeNameForUserCodingSchemeNameByFormalName() throws Exception{
		Registry registryMock = createMock(Registry.class);
		
		RegistryEntry entry = new RegistryEntry();
		entry.setResourceUri("uri");
		entry.setResourceVersion("version");
		entry.setTag("");
		
		List<RegistryEntry> entries = new ArrayList<RegistryEntry>();
		entries.add(entry);
		
		expect(registryMock.getAllRegistryEntriesOfType(ResourceType.CODING_SCHEME)).andReturn(entries);
		replay(registryMock);
		
		DelegatingResourceManagingService service = new DelegatingResourceManagingService();
		CodingSchemeService csService = createMock(CodingSchemeService.class);
		
		CodingScheme cs = new CodingScheme();
		cs.setCodingSchemeName("csName");
		cs.setFormalName("fname");
		cs.setRepresentsVersion("version");
		cs.setCodingSchemeURI("uri");
		cs.addLocalName("someLocalName");
		
		expect(csService.getCodingSchemeByUriAndVersion("uri", "version")).andReturn(cs).once();
		replay(csService);
		
		service.setRegistry(registryMock);
		service.setCodingSchemeService(csService);
		service.afterPropertiesSet();
		
		assertEquals(
				"csName", 
				service.getInternalCodingSchemeNameForUserCodingSchemeName("fname", "version"));
	}
	
	@Test
	public void testGetInternalCodingSchemeNameForUserCodingSchemeNameByCodingSchemeName() throws Exception{
		Registry registryMock = createMock(Registry.class);
		
		RegistryEntry entry = new RegistryEntry();
		entry.setResourceUri("uri");
		entry.setResourceVersion("version");
		entry.setTag("");
		
		List<RegistryEntry> entries = new ArrayList<RegistryEntry>();
		entries.add(entry);
		
		expect(registryMock.getAllRegistryEntriesOfType(ResourceType.CODING_SCHEME)).andReturn(entries);
		replay(registryMock);
		
		DelegatingResourceManagingService service = new DelegatingResourceManagingService();
		CodingSchemeService csService = createMock(CodingSchemeService.class);
		
		CodingScheme cs = new CodingScheme();
		cs.setCodingSchemeName("csName");
		cs.setFormalName("fname");
		cs.setRepresentsVersion("version");
		cs.setCodingSchemeURI("uri");
		cs.addLocalName("someLocalName");
		
		expect(csService.getCodingSchemeByUriAndVersion("uri", "version")).andReturn(cs).once();
		replay(csService);
		
		service.setRegistry(registryMock);
		service.setCodingSchemeService(csService);
		service.afterPropertiesSet();
		
		assertEquals(
				"csName", 
				service.getInternalCodingSchemeNameForUserCodingSchemeName("csName", "version"));
	}
	
	@Test
	public void testGetInternalVersionStringForTag() throws Exception{
		Registry registryMock = createMock(Registry.class);
		
		RegistryEntry entry = new RegistryEntry();
		entry.setResourceUri("uri");
		entry.setResourceVersion("v123");
		entry.setTag("someTag");
		
		List<RegistryEntry> entries = new ArrayList<RegistryEntry>();
		entries.add(entry);
		
		expect(registryMock.getAllRegistryEntriesOfType(ResourceType.CODING_SCHEME)).andReturn(entries);
		expect(registryMock.getEntriesForUri("uri")).andReturn(entries);
		replay(registryMock);
		
		DelegatingResourceManagingService service = new DelegatingResourceManagingService();
		CodingSchemeService csService = createMock(CodingSchemeService.class);
		
		CodingScheme cs = new CodingScheme();
		cs.setCodingSchemeName("csName");
		cs.setFormalName("fname");
		cs.setRepresentsVersion("v123");
		cs.setCodingSchemeURI("uri");
		cs.addLocalName("someLocalName");
		
		expect(csService.getCodingSchemeByUriAndVersion("uri", "v123")).andReturn(cs).once();
		replay(csService);
		
		service.setRegistry(registryMock);
		service.setCodingSchemeService(csService);
		service.afterPropertiesSet();
		
		assertEquals(
				"v123", 
				service.getInternalVersionStringForTag("csName", "someTag"));
	}
	
	@Test(expected=LBParameterException.class)
	public void testGetInternalVersionStringForTagNoMatch() throws Exception{
		Registry registryMock = createMock(Registry.class);
		
		RegistryEntry entry = new RegistryEntry();
		entry.setResourceUri("uri");
		entry.setResourceVersion("v123");
		entry.setTag("_INVALID_someTag");
		
		List<RegistryEntry> entries = new ArrayList<RegistryEntry>();
		entries.add(entry);
		
		expect(registryMock.getAllRegistryEntriesOfType(ResourceType.CODING_SCHEME)).andReturn(entries);
		expect(registryMock.getEntriesForUri("uri")).andReturn(entries);
		replay(registryMock);
		
		DelegatingResourceManagingService service = new DelegatingResourceManagingService();
		CodingSchemeService csService = createMock(CodingSchemeService.class);
		
		CodingScheme cs = new CodingScheme();
		cs.setCodingSchemeName("csName");
		cs.setFormalName("fname");
		cs.setRepresentsVersion("v123");
		cs.setCodingSchemeURI("uri");
		cs.addLocalName("someLocalName");
		
		expect(csService.getCodingSchemeByUriAndVersion("uri", "v123")).andReturn(cs).once();
		replay(csService);
		
		service.setRegistry(registryMock);
		service.setCodingSchemeService(csService);
		service.afterPropertiesSet();
		
		assertEquals(
				"v123", 
				service.getInternalVersionStringForTag("csName", "someTag"));
	}
	
	@Test(expected=LBParameterException.class)
	public void testGetInternalVersionStringForTagMultipleMatch() throws Exception{
		Registry registryMock = createMock(Registry.class);
		
		RegistryEntry entry = new RegistryEntry();
		entry.setResourceUri("uri");
		entry.setResourceVersion("v123");
		entry.setTag("someTag");
		
		RegistryEntry entry2 = new RegistryEntry();
		entry2.setResourceUri("uri");
		entry2.setResourceVersion("v456");
		entry2.setTag("someTag");
		
		List<RegistryEntry> entries = new ArrayList<RegistryEntry>();
		entries.add(entry);
		entries.add(entry2);
		
		expect(registryMock.getAllRegistryEntriesOfType(ResourceType.CODING_SCHEME)).andReturn(entries);
		expect(registryMock.getEntriesForUri("uri")).andReturn(entries).anyTimes();
		replay(registryMock);
		
		DelegatingResourceManagingService service = new DelegatingResourceManagingService();
		CodingSchemeService csService = createMock(CodingSchemeService.class);
		
		CodingScheme cs = new CodingScheme();
		cs.setCodingSchemeName("csName");
		cs.setFormalName("fname");
		cs.setRepresentsVersion("v123");
		cs.setCodingSchemeURI("uri");
		cs.addLocalName("someLocalName");
		
		CodingScheme cs2 = new CodingScheme();
		cs2.setCodingSchemeName("csName");
		cs2.setFormalName("fname");
		cs2.setRepresentsVersion("v456");
		cs2.setCodingSchemeURI("uri");
		cs2.addLocalName("someLocalName");
		
		expect(csService.getCodingSchemeByUriAndVersion("uri", "v123")).andReturn(cs).once();
		expect(csService.getCodingSchemeByUriAndVersion("uri", "v456")).andReturn(cs).once();
		replay(csService);
		
		service.setRegistry(registryMock);
		service.setCodingSchemeService(csService);
		service.afterPropertiesSet();
		
		assertEquals(
				"v123", 
				service.getInternalVersionStringForTag("csName", "someTag"));
	}
	
	@Test
	public void testSearchPerformance() throws Exception{
		int limit = 10000;
		int localNames = 6;
		
		List<CodingSchemeAliasHolder> list = new ArrayList<CodingSchemeAliasHolder>();
		
		for(int i=0;i<limit;i++){
			CodingSchemeAliasHolder holder = new CodingSchemeAliasHolder();
			holder.setCodingSchemeName(String.valueOf(i));
			holder.setCodingSchemeUri(String.valueOf(i));
			holder.setFormalName(String.valueOf(i));

			holder.setLocalNames(new ArrayList<String>());
			for(int j=0;j<localNames;j++){
				holder.getLocalNames().add(String.valueOf(i)+String.valueOf(j));
			}
			holder.setRepresentsVersion(String.valueOf(i));
			list.add(holder);
		}
		
		DelegatingResourceManagingService service = new DelegatingResourceManagingService();
		
		long start = System.currentTimeMillis();
		boolean found = false;
		int skipped = 0;
		for(CodingSchemeAliasHolder alias : list){
			skipped++;
			found = found || service.hasAlias(alias, "90023");
			if(found){
				break;
			}
		}
		long time = System.currentTimeMillis() - start;
		
		assertTrue(found);
		assertTrue("Actual time: " + time, time < 1l);
	}

}
