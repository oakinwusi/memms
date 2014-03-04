/**
 * Copyright (c) 2012, Clinton Health Access Initiative.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.chai.memms.reports.hmis

import java.util.List;
import java.util.Map;
import org.chai.location.Location;
import org.chai.location.DataLocationType;
import org.chai.location.DataLocation;
import org.chai.memms.reports.hmis.HmisEquipmentType;
import org.chai.memms.util.Utils;
import org.chai.memms.inventory.EquipmentService;
/**
 * @author Eric Dusabe, Jean Kahigiso M.
 *
 */
class HmisEquipmentTypeService {

	static transactional = true
	def languageService;
	def locationService
	def grailsApplication
	def equipmentService

	public def searchHmisEquipmentType(String text,Map<String, String> params) {
		text = text.trim()
		def dbFieldName = 'names_'+languageService.getCurrentLanguagePrefix();
		def criteria = HmisEquipmentType.createCriteria()
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			or{
				ilike("code","%"+text+"%")
				ilike(dbFieldName,"%"+text+"%")
			}
		}


	}


	public def getAllEquipmentOfTypeAndCurrentStatus(def equipmentOfType, def status){

	}

	public def generateHmisReport(def dataLocations, boolean quartly){
		def hmisEquipmentTypes = HmisEquipmentType.list()
		for (def dataLocation : dataLocations){
			for (def hmisEquipmentType : hmisEquipmentTypes){
				def numberOfOpEquipment = 0;
				for (def equipmentType: hmisEquipmentType.equipmentTypes){
					def criteria = Equipment.createCriteria()
					println"=> equipmentType: "+equipmentType.id+" numberOfOpEquipment before: "+numberOfOpEquipment
					numberOfOpEquipment =+ criteria.get{
							projections{
					    		rowCount()
							}
							and{
								 eq("dataLocation",dataLocation)
								 eq("type",equipmentType)
							 	//inList("currentStatus",[Status.OPERATIONAL,Status.INSTOCK])
							} 
					}
					println"=> equipmentType: "+equipmentType.id+" numberOfOpEquipment after: "+numberOfOpEquipment
				}
				println"=> dataLocation:"+dataLocation.id+" hmisEquipmentType: "+hmisEquipmentType.id+" numberOfOpEquipment:"+numberOfOpEquipment
				//newHmisFacilityReport(dataLocation,numberOfOpEquipment,quartly)
			}	
		}
		
	}


	private def newHmisFacilityReport(def dataLocation,def hmisEquipmentType,def numberOfOpEquipment, boolean quartly){
		//if(){}

	}
	private def getQuartPeriod(){
		def toDay = new Date();
	}

}
