

public class dados_usuario {

	private final int master_key = 1705;

	private String nome;

	private String senha;

	public String getNome()
	{
		return nome;
	}

	public void getSenha(int x)
	{
		if(x == master_key) {
			System.out.println(senha);
		}
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	private void setSenha(String senha) 
	{
		this.senha = senha;
	}



}
