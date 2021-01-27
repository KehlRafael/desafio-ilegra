import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class DataAnalysis {
	
	private Path filedir;
	
	// Dois construtores, caso tenha ou nao o filedir
	public DataAnalysis(Path param) {
		this.filedir = param;
	}
	
	public DataAnalysis() {
		this(null);
	}

	// Metodo que faz o report
	public void generateReport() {
		// Faz a leitura do arquivo
		String[] token;
		List<Salesperson> salesperson = new ArrayList<Salesperson>();
		List<Customer> customers = new ArrayList<Customer>();
		List<Sales> sales = new ArrayList<Sales>();
		List<String> lines = new ArrayList<String>();
		String filename = (filedir.getFileName()).toString();
		// Pega somente o nome do arquivo pra formatar pro output
		filename = filename.substring(0, filename.length()-4);
		// Assumo que a pasta ja exista
		String path = System.getProperty("user.home")+File.separator+"data"+File.separator+"out";
		Path filepath = Paths.get(path+File.separator+filename+".done.dat");
		
		if (Files.exists(filepath)) {
			System.out.format("File %s already exists.%n",filepath.getFileName());
			// Sai do metodo, continua executando
			return;
		}
		
		try {
			List<String> allLines = Files.readAllLines(filedir);
			for (String line : allLines) {
				token = line.split("ç");
				switch(token[0]) {
				  case "001":
					salesperson.add(parseSalesperson(token));
					break;
				  case "002":
					customers.add(parseCustomer(token));
					break;
				  case "003":
					sales.add(parseSales(token));
					break;
				}
			}
			
			// Faz o output para o arquivo
			lines.add("Number of clients: " + String.valueOf(customers.size()));
			lines.add("Number of salesperson: " + String.valueOf(salesperson.size()));
			lines.add("Most expensive sale: " + bestSale(sales));
			lines.add("Worst salesperson ever: " + worstSalesperson(sales));
			Files.write(filepath, lines, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Salesperson parseSalesperson(String[] token) {
		Salesperson person = new Salesperson(token[1], token[2], Double.parseDouble(token[3]));
		return person;
	}
	
	public Customer parseCustomer(String[] token) {
		Customer customer = new Customer(token[1],token[2],token[3]);
		return customer;
	}
	
	public Sales parseSales(String[] token) {
		// remove "[" e "]"
		String[] tokenItems = (token[2].substring(1, (token[2]).length()-1)).split(",");
		List<Item> items = new ArrayList<Item>();
		double total = 0;
		int i;
		
		// Faz o parse do itens da venda
		for(i=0; i<tokenItems.length; i++) {
			items.add(parseItems((tokenItems[i]).split("-")));
			total = total + (items.get(i)).getPrice()*(items.get(i)).getQuantity();
		}
		
		Sales sales = new Sales(token[1], items, token[3], total);
		return sales;
	}
	
	public Item parseItems(String[] token) {
		Item item = new Item(token[0], Integer.parseInt(token[1]), Double.parseDouble(token[2]));
		return item;
	}
	
	public String bestSale(List<Sales> salesList) {
		String bestId = null;
		double current;
		double best=0;
		int i;
		
		for(i=0; i < salesList.size(); i++) {
			current = salesList.get(i).getTotalSold();
			if (current > best) {
				best = current;
				bestId = salesList.get(i).getSaleId();
			}
		}
		return bestId;
	}
	
	// Aqui eu simplesmente peguei o com a menor venda.
	// Porem, ha a possibilidade de haver mais de uma entrada
	// para um mesmo vendedor, ai teria de acumular por vendedor
	// em um Map<String,double> as vendas. A chave deveria ser o cpf
	public String worstSalesperson(List<Sales> salesList) {
		String worstSalesperson = salesList.get(0).getSalespersonName();
		double worstSale = salesList.get(0).getTotalSold();
		int i;
		
		for(i=1; i < salesList.size(); i++) {
			if (salesList.get(i).getTotalSold() < worstSale) {
				worstSale = salesList.get(i).getTotalSold();
				worstSalesperson = salesList.get(i).getSalespersonName(); 
			}
		}
		
		return worstSalesperson;
	}

	public Path getFiledir() {
		return filedir;
	}

	public void setFiledir(Path filedir) {
		this.filedir = filedir;
	}
}

