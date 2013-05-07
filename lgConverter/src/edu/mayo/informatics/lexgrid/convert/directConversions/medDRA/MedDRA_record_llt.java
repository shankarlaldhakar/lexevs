/*
* Copyright: (c) 2004-2013 Mayo Foundation for Medical Education and
* Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
* triple-shield Mayo logo are trademarks and service marks of MFMER.
*
* Except as contained in the copyright notice above, or as used to identify
* MFMER as the author of this software, the trade names, trademarks, service
* marks, or product names of the copyright holder shall not be used in
* advertising, promotion or otherwise in connection with this software without
* prior written authorization of the copyright holder.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package edu.mayo.informatics.lexgrid.convert.directConversions.medDRA;

import java.io.Serializable;


/**
 *  @author <a href="mailto:hardie.linda@mayo.edu">Linda Hardie</a>
 *
*/
public class MedDRA_record_llt implements Serializable, DatabaseRecord{

	private static final long serialVersionUID = 1L;
	
	private String llt_code;
	private String llt_name;
	private String pt_code;
	private String llt_whoart_code;
	private String llt_harts_code;
	private String llt_costart_sym;
	private String llt_icd9_code;
	private String llt_icd9cm_code;
	private String llt_icd10_code;
	private String llt_currency;
	private String llt_jart_code;
	
	
	public String getLlt_code() {
		return llt_code;
	}


	public void setLlt_code(String llt_code) {
		this.llt_code = llt_code;
	}


	public String getLlt_name() {
		return llt_name;
	}


	public void setLlt_name(String llt_name) {
		this.llt_name = llt_name;
	}


	public String getPt_code() {
		return pt_code;
	}


	public void setPt_code(String pt_code) {
		this.pt_code = pt_code;
	}


	public String getLlt_whoart_code() {
		return llt_whoart_code;
	}


	public void setLlt_whoart_code(String llt_whoart_code) {
		this.llt_whoart_code = llt_whoart_code;
	}


	public String getLlt_harts_code() {
		return llt_harts_code;
	}


	public void setLlt_harts_code(String llt_harts_code) {
		this.llt_harts_code = llt_harts_code;
	}


	public String getLlt_costart_sym() {
		return llt_costart_sym;
	}


	public void setLlt_costart_sym(String llt_costart_sym) {
		this.llt_costart_sym = llt_costart_sym;
	}


	public String getLlt_icd9_code() {
		return llt_icd9_code;
	}


	public void setLlt_icd9_code(String llt_icd9_code) {
		this.llt_icd9_code = llt_icd9_code;
	}


	public String getLlt_icd9cm_code() {
		return llt_icd9cm_code;
	}


	public void setLlt_icd9cm_code(String llt_icd9cm_code) {
		this.llt_icd9cm_code = llt_icd9cm_code;
	}


	public String getLlt_icd10_code() {
		return llt_icd10_code;
	}


	public void setLlt_icd10_code(String llt_icd10_code) {
		this.llt_icd10_code = llt_icd10_code;
	}


	public String getLlt_currency() {
		return llt_currency;
	}


	public void setLlt_currency(String llt_currency) {
		this.llt_currency = llt_currency;
	}


	public String getLlt_jart_code() {
		return llt_jart_code;
	}


	public void setLlt_jart_code(String llt_jart_code) {
		this.llt_jart_code = llt_jart_code;
	}
}
