package pojo;

import java.util.List;

public class Courses {

	private List<WebAutomation> WebAutomation;
	private List<api> api;
	private List<mobile> mobile;

	public List<WebAutomation> getWebAutomation() {
		return WebAutomation;
	}

	public void setWebAutomation(List<WebAutomation> webAutomation) {
		WebAutomation = webAutomation;
	}

	public List<api> getApi() {
		return api;
	}

	public void setApi(List<api> api) {
		this.api = api;
	}

	public List<mobile> getMobile() {
		return mobile;
	}

	public void setMobile(List<mobile> mobile) {
		this.mobile = mobile;
	}

}
