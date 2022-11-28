package lab;

public class Registration {	
    private String containNumberPattern = ".*\\d.*";
	
    public boolean signUp(String email, String password) {
        return isValidEmail(email) && isValidPassword(password);
    }
	
    /*
     * 檢查使用者輸入的Email使否符合Email語法規則
     */
    boolean isValidEmail(String email) {
    	
    	// 驗證電子郵件格式 
        if(email.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$")){
            //System.out.println("格式正確"); 
        	return true;
        }else{
            //System.out.println("格式錯誤");
        	return false;
        }
//        if(email.contains("@")) {
//            return true;
//        }
//        return false;
    }
    /*
    * 檢查使用者輸入的password是否符合下列規則
    * 1. 必須包含數字
    * 2. 必須包含大寫字元
    * 3. 字元長度必須大於六
    */
    boolean isValidPassword(String password) {
        if(!password.matches(containNumberPattern)) {
            return false;
        }
		
        if(password.equals(password.toLowerCase())) {
        	return false;
        }	
        
        if(password.length()<=6){
        	return false;
        }	
        return true;
    }
}

