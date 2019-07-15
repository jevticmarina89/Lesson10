package exercises;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationOfData {

	public boolean validPhoneNumber(String phoneNum) {
		Pattern pattern = Pattern.compile("^\\d+(\\s|-)\\d+-?\\d+$");
		Matcher matcher = pattern.matcher(phoneNum);
		return matcher.find();
	}

	public boolean validEmailAddress(String email) {
		Pattern pattern = Pattern.compile("\\w+\\d*@{1}\\w+(\\.com)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	
	}
	
	

}
