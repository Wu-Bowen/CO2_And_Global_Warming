import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class CleanMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] splitLine = value.toString().split(",", -1);
		if (splitLine[0]!= null && !splitLine[0].isEmpty() && !splitLine[0].equals("OWID_WRL")&& !splitLine[1].equals("country")) {
			if(splitLine[3].isEmpty()) {
				context.write(new Text(splitLine[1] + "," + splitLine[2] + ",0.0"), new Text(""));
				}else{
				context.write(new Text(splitLine[1] + "," + splitLine[2] +"," + splitLine[3]), new Text(""));
			}
		}
	}
}
