
public class Salesperson {
	
	private String cpf;
	private String name;
	private double salary;
	
	// Nao sera feito aritmetica com cpf, usei double para
	// salario pois nao sei quais operacoes serao feitas com ele.
	// Se nenhuma operacao for realizada, int para salvar com
	// precisão total e so divide por 100 para recuperar o valor
	public Salesperson(String cpf, String name, double salary) {
		this.setCpf(cpf);
		this.setName(name);
		this.setSalary(salary);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
