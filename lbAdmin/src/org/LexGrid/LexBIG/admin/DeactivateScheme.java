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
package org.LexGrid.LexBIG.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeSummary;
import org.LexGrid.LexBIG.DataModel.InterfaceElements.CodingSchemeRendering;
import org.LexGrid.LexBIG.Impl.LexBIGServiceImpl;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.LexBIGService.LexBIGServiceManager;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.LexGrid.annotations.LgAdminFunction;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.lexevs.system.ResourceManager;

/**
 * Deactivates a coding scheme based on unique URN and version.
 * 
 * User sees menu of coding schemes to deactivate and inputs number
 * interface requests activation confirmation as Y or N
 * 
 * Otherwise command line request can be made as follows
 * <pre>
 * Example: java org.LexGrid.LexBIG.admin.DeactivateScheme
 *   -u,--urn &lt;name&gt; URN uniquely identifying the code system.
 *   -v,--version &lt;id&gt; Version identifier.
 *   -f,--force Force deactivation (no confirmation).
 * 
 * Note: If the URN and version values are unspecified, a
 * list of available coding schemes will be presented for
 * user selection.
 * 
 * Example: java -Xmx512m -cp lgRuntime.jar
 *  org.LexGrid.LexBIG.admin.DeactivateScheme
 *    -u &quot;urn:oid:2.16.840.1.113883.3.26.1.1&quot; -v &quot;05.09e&quot;
 * </pre>
 * 
 * @author <A HREF="mailto:johnson.thomas@mayo.edu">Thomas Johnson</A>
 */
@LgAdminFunction
public class DeactivateScheme {
    static DateFormat _df = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");

    public static void main(String[] args) {
        try {
            new DeactivateScheme().run(args);
        } catch (Exception e) {
            Util.displayAndLogError("REQUEST FAILED !!!", e);
        }
    }

    public DeactivateScheme() {
        super();
    }

    /**
     * Primary entry point for the program.
     * 
     * @throws Exception
     */
    public void run(String[] args) throws Exception {
        synchronized (ResourceManager.instance()) {

            // Parse the command line ...
            CommandLine cl = null;
            Options options = getCommandOptions();
            Date when = null;
            try {
                cl = new BasicParser().parse(options, args);
                if (cl.hasOption("d"))
                    when = _df.parse(cl.getOptionValue("d"));
            } catch (Exception e) {
                Util
                        .displayCommandOptions(
                                "DeactivateScheme",
                                options,
                                "DeactivateScheme -u \"urn:oid:2.16.840.1.113883.3.26.1.1\" -v \"05.09e\" ",
                                e);
                Util.displayMessage(Util.getPromptForSchemeHelp());
                return;
            }

            // Interpret provided values ...
            String urn = cl.getOptionValue("u");
            String ver = cl.getOptionValue("v");
            boolean force = cl.hasOption("f");
            CodingSchemeSummary css = null;

            // Find in list of registered vocabularies ...
            if (urn != null && ver != null) {
                urn = urn.trim();
                ver = ver.trim();
                LexBIGService lbs = LexBIGServiceImpl.defaultInstance();
                Enumeration<? extends CodingSchemeRendering> schemes = lbs.getSupportedCodingSchemes()
                        .enumerateCodingSchemeRendering();
                while (schemes.hasMoreElements() && css == null) {
                    CodingSchemeSummary summary = schemes.nextElement().getCodingSchemeSummary();
                    if (urn.equalsIgnoreCase(summary.getCodingSchemeURI())
                            && ver.equalsIgnoreCase(summary.getRepresentsVersion()))
                        css = summary;
                }
            }

            // Found it? If not, prompt...
            if (css == null) {
                if (urn != null || ver != null) {
                    Util.displayMessage("No matching coding scheme was found for the given URN or version.");
                    Util.displayMessage("");
                }
                css = Util.promptForCodeSystem();
                if (css == null)
                    return;
            }

            // Continue and confirm the action (if not bypassed by force option)
            // ...
            Util.displayTaggedMessage("A matching coding scheme was found ...");
            boolean confirmed = true;
            if (!force) {
                Util.displayMessage((when == null ? "DEACTIVATE NOW?" : ("DEACTIVATE ON: " + when.toString()))
                        + " ('Y' to confirm, any other key to cancel)");
                char choice = Util.getConsoleCharacter();
                confirmed = choice == 'Y' || choice == 'y';
            }
            if (confirmed) {
                LexBIGServiceManager lbsm = LexBIGServiceImpl.defaultInstance().getServiceManager(null);
                lbsm.deactivateCodingSchemeVersion(Constructors.createAbsoluteCodingSchemeVersionReference(css), when);
                Util.displayTaggedMessage("Request complete");
            } else {
                Util.displayTaggedMessage("Action cancelled by user");
            }
        }
    }

    /**
     * Return supported command options.
     * 
     * @return org.apache.commons.cli.Options
     */
    private Options getCommandOptions() {
        Options options = new Options();
        Option o;

        o = new Option("u", "urn", true, "URN uniquely identifying the code system.");
        o.setArgName("name");
        o.setRequired(false);
        options.addOption(o);

        o = new Option("v", "version", true, "Version identifier.");
        o.setArgName("id");
        o.setRequired(false);
        options.addOption(o);

        o = new Option("d", "date", true, "Date and time for deactivation to take effect; immediate if not specified.");
        o.setArgName("yyyy-MM-dd,HH:mm:ss");
        o.setRequired(false);
        options.addOption(o);

        o = new Option("f", "force", false, "Force deactivation (no confirmation).");
        o.setRequired(false);
        options.addOption(o);

        return options;
    }

}