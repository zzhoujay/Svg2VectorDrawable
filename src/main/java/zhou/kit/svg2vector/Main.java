package zhou.kit.svg2vector;

import java.io.*;

/**
 * Created by zhou on 15-11-29.
 */
public class Main {

    private static final String test = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\">\n" +
            "    <path fill=\"none\" d=\"M0 0h24v24H0V0z\"/>\n" +
            "    <path d=\"M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14zM7 9h5v1H7z\"/>\n" +
            "</svg>\n";

    public static void main(String... args) throws IOException {

        String input = args[0];
        String output = args[1];

        File inputFile = new File(input);
        File outputFile = new File(output);

        if (!inputFile.exists()) {
            throw new FileNotFoundException("input file don't exist");
        }

        if (inputFile.isDirectory()) {
            if (!outputFile.isDirectory()) {
                outputFile.mkdirs();
            }
            File[] files = inputFile.listFiles();
            if (files != null) {
                int count = 0;
                int len = files.length;
                for (File file : files) {
                    count++;
                    System.out.println(String.format("%d/%d", count, len));
                    conver(file, new File(outputFile, wipeSuffix(file.getName())+".xml"));
                }
            } else {
                System.out.println("文件夹下没有文件");
            }
        } else {
            conver(inputFile, outputFile);
        }
    }

    public static String wipeSuffix(String name) {
        return name.substring(0,name.lastIndexOf("."));
    }

    public static void conver(File input, File output) throws IOException {
        FileReader fileReader = new FileReader(input);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        String out = Svg2VectorParser.parse(stringBuilder.toString());
        if (out != null) {
            FileWriter fileWriter = new FileWriter(output);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(out);
            bufferedWriter.flush();
            bufferedWriter.close();
            System.out.println("转换成功:" + output.getAbsolutePath());
        } else {
            System.out.println("转换失败");
        }
    }
}
