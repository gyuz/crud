package crud.core.model;

public class Role {
    private int roleId;
    private String roleName;    

    public Role(){
    }
    
    public Role(String roleName){
        setRoleName(roleName);    
    }
    
    public void setRoleId(int roleId){
        this.roleId = roleId;    
    }
    
    public void setRoleName(String roleName){
        this.roleName = roleName;        
    }
    
    public int getRoleId(){
        return roleId;    
    }    

    public String getRoleName(){
        return roleName;    
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;
        Role obj2 = (Role) obj;
        if(this.roleName.equals(obj2.roleName))
        {
            return true;
        }
        return false;
    }

    public int hashCode() {
    int tmp = 0;
    tmp = ( roleName ).hashCode();
    return tmp;
    }
}
