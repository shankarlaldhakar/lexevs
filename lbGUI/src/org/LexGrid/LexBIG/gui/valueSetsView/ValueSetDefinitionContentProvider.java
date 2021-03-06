/*
 * Copyright: (c) 2004-2010 Mayo Foundation for Medical Education and 
 * Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
 * triple-shield Mayo logo are trademarks and service marks of MFMER.
 *
 * Except as contained in the copyright notice above, or as used to identify 
 * MFMER as the author of this software, the trade names, trademarks, service
 * marks, or product names of the copyright holder shall not be used in
 * advertising, promotion or otherwise in connection with this software without
 * prior written authorization of the copyright holder.
 * 
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 * 		http://www.eclipse.org/legal/epl-v10.html
 * 
 */
package org.LexGrid.LexBIG.gui.valueSetsView;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Exceptions.LBInvocationException;
import org.LexGrid.LexBIG.gui.LB_VSD_GUI;
import org.LexGrid.valueSets.ValueSetDefinition;
import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Content provider for the Value Set Definition SWT Table.
 * 
 * @author <A HREF="mailto:dwarkanath.sridhar@mayo.edu">Sridhar Dwarkanath</A>
 * @version subversion $Revision: $ checked in on $Date: $
 */
public class ValueSetDefinitionContentProvider implements IStructuredContentProvider {
    private LB_VSD_GUI lbVDGui_;
    private static Logger log = Logger.getLogger("LB_VSGUI_LOGGER");
    private org.LexGrid.valueSets.ValueSetDefinition[] currentVDRenderings_ = null;

    public ValueSetDefinitionContentProvider(LB_VSD_GUI lbVDGui) {
        lbVDGui_ = lbVDGui;
    }

    public Object[] getElements(Object arg0) {
        try {
            return getValueSetDefinitions();
        } catch (LBInvocationException e) {
            log.error("Unexpected Error", e);
            return new String[] {};
        } catch (URISyntaxException e) {
            log.error("Unexpected Error", e);
            return new String[] {};
        }
    }

    public void dispose() {
        // do nothing
    }

    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
        currentVDRenderings_ = null;
    }

    private org.LexGrid.valueSets.ValueSetDefinition[] getValueSetDefinitions()
            throws LBInvocationException, URISyntaxException {
        if (currentVDRenderings_ == null) {
            if (lbVDGui_.getValueSetDefinitionService() != null) {
                try {
                    List<String> uris = lbVDGui_.getValueSetDefinitionService().listValueSetDefinitionURIs();
                    if (uris.size() > 0)
                    {
                        currentVDRenderings_ = new ValueSetDefinition[uris.size()];
                        for (int i = 0; i < uris.size(); i++)
                        {
                            currentVDRenderings_[i] = lbVDGui_.getValueSetDefinitionService().getValueSetDefinition(new URI(uris.get(i)), null);
                        }
                    } else {
                        currentVDRenderings_ = new ValueSetDefinition[] {};
                    }
                } catch (LBException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Arrays.sort(currentVDRenderings_,
                        new ValueSetDefRenderingComparator());
            } else {
                currentVDRenderings_ = new ValueSetDefinition[] {};
            }

        }
        return currentVDRenderings_;
    }

    private class ValueSetDefRenderingComparator implements
            Comparator<ValueSetDefinition> {

        public int compare(ValueSetDefinition o1, ValueSetDefinition o2) {
            return o1.getValueSetDefinitionURI()
                    .compareToIgnoreCase(
                            o2.getValueSetDefinitionURI());
        }

    }

}