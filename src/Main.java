import java.util.Scanner;
import java.sql.*;

public class Main {

	public static void main(String[] args) throws Exception{
		Scanner scanner = new Scanner(System.in);
		login();

	}

	public static void login() throws Exception 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Bem vindo ao banco" + "\n" + "\n1 - cadastro\n2 - login" + "\n");
		int choice = scanner.nextInt();
		switch(choice) {
		case 1 :
			while(1 > 0) 
			{
			int x = sql_new_user();
			if (x == 1) {break;}
			}
		case 2 :
			while (1 > 0) 
			{
			int y = sql_login_user();
			if (y == 1) {
				System.out.println("logado com sucesso");
				break;}
		    }
		}
	}

	public static int sql_new_user() throws Exception
    {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o seu nome de usuario : \n");
		String user = scanner.next();
		String query = "select nome from cadastros_usuarios where nome = '"+ user +"';";
		String url = "jdbc:mysql://localhost:3306/banco_java";
		String user_sql = "root";
		String pass = "Lns!181417";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url , user_sql, pass);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		String user_name = "";

		while(rs.next()) 
		{
		user_name = rs.getString("nome"); 

		if(user_name.equals(user)) {
			System.out.printf("\n"+"[O nome de usuario (%s) já está em uso, tente novamente]\n"+"\n", user_name);
			st.close();
			rs.close();
			return 0;} 

		}

		System.out.println("Digite a senha para sua nova conta :\n(obs : a senha deve conter no maximo 8 caracteres)\n");
		String senha = scanner.next();
		String query_cadastro = "insert into cadastros_usuarios (nome, senha) values ('"+ user +"', '"+ senha +"');";
		int cadastro_final = st.executeUpdate(query_cadastro);
		System.out.println("\n"+"[Novo usuario cadastrado com sucesso]\n");

		st.close();
		rs.close();
		return 1;
	}

	public static int sql_login_user() throws Exception 
	{

		int id; 
		String user_ask;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite o seu nome de usuario : \n");
		String user = scanner.next();
		String url = "jdbc:mysql://localhost:3306/banco_java";
		String user_sql = "root";
		String pass = "Lns!181417";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url , user_sql, pass);
		Statement st1 = con.createStatement();
		Statement st2 = con.createStatement();


		int l = 0;
		while (l != 1) 
		{	
			String query = "select nome from cadastros_usuarios where nome = '"+ user +"';";
			ResultSet rs = st1.executeQuery(query);
			while (rs.next()) 
			{
				user_ask = rs.getString("nome");
				if (user.equals(user_ask)) {l = 1;}
			}
			if (l == 0) 
			{
				System.out.println("\n[usuario não encontrado, tente novamente]\n");
				System.out.println("Digite o seu nome de usuario : \n");
				user = scanner.next();
			}
		}



        l = 0;
		while (l != 1) 
		{
			System.out.printf("Digite a senha do usuario (%s) : \n", user);
			String senha = scanner.next();
			String query2 = "select senha from cadastros_usuarios where nome = '"+ user +"';";
			ResultSet select_senha = st2.executeQuery(query2);
			String senha_ask = "";
			while (select_senha.next()) 
			{
				senha_ask = select_senha.getString("senha");
				if (senha_ask.equals(senha)) {l = 1;}
			}
			if(l == 0) {System.out.println("\n[senha incorreta, tente novamente]\n");}
		}


		return 1;
	}
}
