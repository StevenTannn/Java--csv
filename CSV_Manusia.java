import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

public class CSV_Manusia implements CSV<Manusia> {

    @Override
    public void write(Path path, List<Manusia> items) {
        try (FileWriter writer = new FileWriter(path.toString());) {
            StringBuilder hasil = new StringBuilder();
            for (Manusia human : items) {
                hasil.append(human.getNama());
                hasil.append(",");
                hasil.append(human.getUmur());
                hasil.append(",");
                hasil.append(human.getBerat());
                hasil.append(",");
                hasil.append(human.getJenisKelamin());
                hasil.append("\n");
            }
            writer.write(hasil.toString());
        } catch (IOException error) {
            System.err.println("gagal");
        }
    }

    @Override
    public List<Manusia> read(Path path) {

        List<Manusia> hasil = new ArrayList<Manusia>();

        try (BufferedReader baca = new BufferedReader(new FileReader(path.toString()))) {
            String line;
            while ((line = baca.readLine()) != null) {
                String[] manusia = line.split(",");
                hasil.add(new Manusia(manusia[0], Integer.parseInt(manusia[1]), Double.parseDouble(manusia[2]),
                        Boolean.parseBoolean(manusia[3])));
            }
        } catch (IOException error) {
            System.err.println("gagal");
        }
        return hasil;
    }
}
