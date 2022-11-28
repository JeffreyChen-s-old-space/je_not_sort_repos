package lab;

public class Registration {	
    private String containNumberPattern = ".*\\d.*";
	
    public boolean signUp(String email, String password) {
        return isValidEmail(email) && isValidPassword(password);
    }
	
    /*
     * �ˬd�ϥΪ̿�J��Email�ϧ_�ŦXEmail�y�k�W�h
     */
    boolean isValidEmail(String email) {
    	
    	// ���ҹq�l�l��榡 
        if(email.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$")){
            //System.out.println("�榡���T"); 
        	return true;
        }else{
            //System.out.println("�榡���~");
        	return false;
        }
//        if(email.contains("@")) {
//            return true;
//        }
//        return false;
    }
    /*
    * �ˬd�ϥΪ̿�J��password�O�_�ŦX�U�C�W�h
    * 1. �����]�t�Ʀr
    * 2. �����]�t�j�g�r��
    * 3. �r�����ץ����j��
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

