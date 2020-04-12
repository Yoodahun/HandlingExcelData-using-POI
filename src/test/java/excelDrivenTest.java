import excelData.dataDriven;

import java.io.IOException;
import java.util.ArrayList;

public class excelDrivenTest {

    public static void main(String[] args) throws IOException {
        dataDriven datadriven = new dataDriven();
        ArrayList<String> datas = datadriven.getData("Add Profile");

        for (String data :
                datas) {
            System.out.println(data);
        }

    }
}
