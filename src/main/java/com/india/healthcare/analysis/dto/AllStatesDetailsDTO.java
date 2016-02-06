package com.india.healthcare.analysis.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/*
 * There is a particular reason why I did not create a set or list of StatewiseDetails DTO
 * and created a separate object for each state. ;) :)
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class AllStatesDetailsDTO {

	@JsonProperty("MH")
	StatewiseDetailsDTO MH;

	@JsonProperty("UP")
	StatewiseDetailsDTO UP;
	
	@JsonProperty("BR")
	StatewiseDetailsDTO BR;
	
	@JsonProperty("WB")
	StatewiseDetailsDTO WB;
	
	@JsonProperty("AP")
	StatewiseDetailsDTO AP;
	
	@JsonProperty("MP")
	StatewiseDetailsDTO MP;
	
	@JsonProperty("TN")
	StatewiseDetailsDTO TN;
	
	@JsonProperty("RJ")
	StatewiseDetailsDTO RJ;
	
	@JsonProperty("KA")
	StatewiseDetailsDTO KA;
	
	@JsonProperty("GJ")
	StatewiseDetailsDTO GJ;
	
	@JsonProperty("OR")
	StatewiseDetailsDTO OR;
	
	@JsonProperty("KL")
	StatewiseDetailsDTO KL;
	
	@JsonProperty("JH")
	StatewiseDetailsDTO JH;
	
	@JsonProperty("AS")
	StatewiseDetailsDTO AS;
	
	@JsonProperty("PB")
	StatewiseDetailsDTO PB;
	
	@JsonProperty("CT")
	StatewiseDetailsDTO CT;
	
	@JsonProperty("HR")
	StatewiseDetailsDTO HR;
	
	@JsonProperty("DL")
	StatewiseDetailsDTO DL;
	
	@JsonProperty("JK")
	StatewiseDetailsDTO JK;
	
	@JsonProperty("UT")
	StatewiseDetailsDTO UT;
	
	@JsonProperty("HP")
	StatewiseDetailsDTO HP;
	
	@JsonProperty("TR")
	StatewiseDetailsDTO TR;
	
	@JsonProperty("ML")
	StatewiseDetailsDTO ML;
	
	@JsonProperty("MN")
	StatewiseDetailsDTO MN;
	
	@JsonProperty("NL")
	StatewiseDetailsDTO NL;
	
	@JsonProperty("GA")
	StatewiseDetailsDTO GA;
	
	@JsonProperty("AR")
	StatewiseDetailsDTO AR;
	
	@JsonProperty("PY")
	StatewiseDetailsDTO PY;
	
	@JsonProperty("MZ")
	StatewiseDetailsDTO MZ;
	
	@JsonProperty("CH")
	StatewiseDetailsDTO CH;
	
	@JsonProperty("SK")
	StatewiseDetailsDTO SK;
	
	@JsonProperty("AN")
	StatewiseDetailsDTO AN;
	
	@JsonProperty("DN")
	StatewiseDetailsDTO DN;
	
	@JsonProperty("DD")
	StatewiseDetailsDTO DD;
	
	@JsonProperty("LD")
	StatewiseDetailsDTO LD;

	public StatewiseDetailsDTO getMH() {
		return MH;
	}

	public void setMH(StatewiseDetailsDTO mH) {
		MH = mH;
	}

	public StatewiseDetailsDTO getUP() {
		return UP;
	}

	public void setUP(StatewiseDetailsDTO uP) {
		UP = uP;
	}

	public StatewiseDetailsDTO getBR() {
		return BR;
	}

	public void setBR(StatewiseDetailsDTO bR) {
		BR = bR;
	}

	public StatewiseDetailsDTO getWB() {
		return WB;
	}

	public void setWB(StatewiseDetailsDTO wB) {
		WB = wB;
	}

	public StatewiseDetailsDTO getAP() {
		return AP;
	}

	public void setAP(StatewiseDetailsDTO aP) {
		AP = aP;
	}

	public StatewiseDetailsDTO getMP() {
		return MP;
	}

	public void setMP(StatewiseDetailsDTO mP) {
		MP = mP;
	}

	public StatewiseDetailsDTO getTN() {
		return TN;
	}

	public void setTN(StatewiseDetailsDTO tN) {
		TN = tN;
	}

	public StatewiseDetailsDTO getRJ() {
		return RJ;
	}

	public void setRJ(StatewiseDetailsDTO rJ) {
		RJ = rJ;
	}

	public StatewiseDetailsDTO getKA() {
		return KA;
	}

	public void setKA(StatewiseDetailsDTO kA) {
		KA = kA;
	}

	public StatewiseDetailsDTO getGJ() {
		return GJ;
	}

	public void setGJ(StatewiseDetailsDTO gJ) {
		GJ = gJ;
	}

	public StatewiseDetailsDTO getOR() {
		return OR;
	}

	public void setOR(StatewiseDetailsDTO oR) {
		OR = oR;
	}

	public StatewiseDetailsDTO getKL() {
		return KL;
	}

	public void setKL(StatewiseDetailsDTO kL) {
		KL = kL;
	}

	public StatewiseDetailsDTO getJH() {
		return JH;
	}

	public void setJH(StatewiseDetailsDTO jH) {
		JH = jH;
	}

	public StatewiseDetailsDTO getAS() {
		return AS;
	}

	public void setAS(StatewiseDetailsDTO aS) {
		AS = aS;
	}

	public StatewiseDetailsDTO getPB() {
		return PB;
	}

	public void setPB(StatewiseDetailsDTO pB) {
		PB = pB;
	}

	public StatewiseDetailsDTO getCT() {
		return CT;
	}

	public void setCT(StatewiseDetailsDTO cT) {
		CT = cT;
	}

	public StatewiseDetailsDTO getHR() {
		return HR;
	}

	public void setHR(StatewiseDetailsDTO hR) {
		HR = hR;
	}

	public StatewiseDetailsDTO getDL() {
		return DL;
	}

	public void setDL(StatewiseDetailsDTO dL) {
		DL = dL;
	}

	public StatewiseDetailsDTO getJK() {
		return JK;
	}

	public void setJK(StatewiseDetailsDTO jK) {
		JK = jK;
	}

	public StatewiseDetailsDTO getUT() {
		return UT;
	}

	public void setUT(StatewiseDetailsDTO uT) {
		UT = uT;
	}

	public StatewiseDetailsDTO getHP() {
		return HP;
	}

	public void setHP(StatewiseDetailsDTO hP) {
		HP = hP;
	}

	public StatewiseDetailsDTO getTR() {
		return TR;
	}

	public void setTR(StatewiseDetailsDTO tR) {
		TR = tR;
	}

	public StatewiseDetailsDTO getML() {
		return ML;
	}

	public void setML(StatewiseDetailsDTO mL) {
		ML = mL;
	}

	public StatewiseDetailsDTO getMN() {
		return MN;
	}

	public void setMN(StatewiseDetailsDTO mN) {
		MN = mN;
	}

	public StatewiseDetailsDTO getNL() {
		return NL;
	}

	public void setNL(StatewiseDetailsDTO nL) {
		NL = nL;
	}

	public StatewiseDetailsDTO getGA() {
		return GA;
	}

	public void setGA(StatewiseDetailsDTO gA) {
		GA = gA;
	}

	public StatewiseDetailsDTO getAR() {
		return AR;
	}

	public void setAR(StatewiseDetailsDTO aR) {
		AR = aR;
	}

	public StatewiseDetailsDTO getPY() {
		return PY;
	}

	public void setPY(StatewiseDetailsDTO pY) {
		PY = pY;
	}

	public StatewiseDetailsDTO getMZ() {
		return MZ;
	}

	public void setMZ(StatewiseDetailsDTO mZ) {
		MZ = mZ;
	}

	public StatewiseDetailsDTO getCH() {
		return CH;
	}

	public void setCH(StatewiseDetailsDTO cH) {
		CH = cH;
	}

	public StatewiseDetailsDTO getSK() {
		return SK;
	}

	public void setSK(StatewiseDetailsDTO sK) {
		SK = sK;
	}

	public StatewiseDetailsDTO getAN() {
		return AN;
	}

	public void setAN(StatewiseDetailsDTO aN) {
		AN = aN;
	}

	public StatewiseDetailsDTO getDN() {
		return DN;
	}

	public void setDN(StatewiseDetailsDTO dN) {
		DN = dN;
	}

	public StatewiseDetailsDTO getDD() {
		return DD;
	}

	public void setDD(StatewiseDetailsDTO dD) {
		DD = dD;
	}

	public StatewiseDetailsDTO getLD() {
		return LD;
	}

	public void setLD(StatewiseDetailsDTO lD) {
		LD = lD;
	}
	
	

}
