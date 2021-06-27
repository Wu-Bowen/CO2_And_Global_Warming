import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Clean {
	public static void main(String[] args) throws Exception {
		Job job = new Job();
		job.setJarByClass(Clean.class);
		job.setJobName("Clean");
		// input
		FileInputFormat.addInputPath(job, new Path(args[0]));
		// output
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapperClass(CleanMapper.class);
		job.setReducerClass(CleanReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		// 1 reduce task
		job.setNumReduceTasks(1);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
