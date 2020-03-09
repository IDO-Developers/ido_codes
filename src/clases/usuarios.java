package clases;

public class usuarios {

	//users
	int id;
	String RNE_Empleado;
	String password;
	String Id_Rol;
	String name;
	//Prematricula
	int Id_Prematricula;
	int Id_Grupo;
	//String RNE_Empleado;
	
	
	

	public int getId() {
		return id;
	}

	public int getId_Prematricula() {
		return Id_Prematricula;
	}

	public void setId_Prematricula(int id_Prematricula) {
		Id_Prematricula = id_Prematricula;
	}

	public int getId_Grupo() {
		return Id_Grupo;
	}

	public void setId_Grupo(int id_Grupo) {
		Id_Grupo = id_Grupo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRNE_Empleado() {
		return RNE_Empleado;
	}

	public void setRNE_Empleado(String rNE_Empleado) {
		RNE_Empleado = rNE_Empleado;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId_Rol() {
		return Id_Rol;
	}

	public void setId_Rol(String id_Rol) {
		Id_Rol = id_Rol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
