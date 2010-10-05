package org.lexgrid.exporter.owlrdf.converter;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDFS;

public class LexRdf {
	/** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model model = ModelFactory.createDefaultModel();
    
    public static Property isDefined = model.createProperty(LexRdfConstants.LEXRDF_IS_DEFINED);
    public static Property isPreferred = model.createProperty(LexRdfConstants.LEXRDF_IS_PREFERRED);
    public static Property degreeOfFidelity = model.createProperty(LexRdfConstants.LEXRDF_DEGREE_OF_FIDELITY);
    public static Property matchIfNoContext = model.createProperty(LexRdfConstants.LEXRDF_MATCH_IF_NO_CONTEXT);
    public static Property representationalForm = model.createProperty(LexRdfConstants.LEXRDF_REPRESENTATIONAL_FORM);
    public static Property propertyLink	= model.createProperty(LexRdfConstants.LEXRDF_PROPERTY_LINK);
}
