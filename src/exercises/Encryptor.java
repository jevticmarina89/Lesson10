package exercises;

public class Encryptor {

	public static String encrypt(String encryptMe) {
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < encryptMe.length(); i++) {
			int numericalValue = encryptMe.charAt(i);
			numericalValue+=2;
			builder.append((char) numericalValue);
		}
		return builder.toString();
	}

	public static String decrypt(String stringToDecrypt) {
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < stringToDecrypt.length(); i++) {
			int index = stringToDecrypt.charAt(i);
			index -= 2;
			builder.append((char)index);
		}
		return builder.toString();
	}


}
