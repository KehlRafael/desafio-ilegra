import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import java.io.*;

public class WatchDir {
	
	private final WatchService watcher;
    private final Path dir;
    private static DataAnalysis data = new DataAnalysis();
	
	WatchDir(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        dir.register(watcher, ENTRY_CREATE);
        this.dir = dir;
    }
    
    // Processa os eventos
    void processEvents() {
        for (;;) {
 
            // Chave para o servico
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }
 
            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
 
                if (kind == OVERFLOW) {
                    continue;
                }
                
                // Se e um arquivo novo sendo adicionado
                if (kind == ENTRY_CREATE) {
                	// Nome do arquivo é o contexto do evento
                    WatchEvent<Path> ev = (WatchEvent<Path>)event;
                    Path filename = ev.context();
     
                    // Testa se o arquivo e .dat
                    if ((filename.toString()).endsWith(".dat")) {
                    	data.setFiledir(dir.resolve(filename));
                    	data.generateReport();
                    }
                }
            }
 
            // Reseta a chave pra continuar o loop
            boolean valid = key.reset();
            if (!valid) {
                    break;
            }
        }
    }

	public static void main(String[] args) {
		// Assumo que a pasta ja exista
		String path = System.getProperty("user.home")+File.separator+"data"+File.separator+"in";
        Path dir = Paths.get(path);
        File filesList[] = (dir.toFile()).listFiles();
        
        // Processa todos arquvios na pasta
        for(File file : filesList) {
        	data.setFiledir(file.toPath());
        	data.generateReport();
        }
        // Registra a pasta e processa os eventos
		try {
			new WatchDir(dir).processEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
