package name.reerink.musicserver.files;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Traverser {

	private File dir = null;

	private Queue<File> queue = new ConcurrentLinkedQueue<File>();

	public Traverser(File dir) {
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException("Not a folder: "
					+ dir.getAbsolutePath());
		}
		this.dir = dir;
		this.queue.add(dir);
	}

	public void traverse(Callback c) {
		File work;
		while ((work = this.queue.poll()) != null) {
			if (work.isDirectory()) {
				File[] files = work.listFiles();
				for (File file : files) {
					if (file.isDirectory()) {
						//System.out.println("Dir: " + file.getPath());
						c.onFolder(file);
						this.queue.add(file);
					} else {
						if (file.isFile()) {
							//System.out.println("File: " + file.getPath());
							c.onFile(file);
						} else {
							//System.out.println("Error, what is: " + file.getPath());
							c.onError(file);
						}
					}
				}
			}
		}
	}

	/**
	 * Show the contents of the root folder.
	 */
	public void showRoot() {
		String[] fileNames = this.dir.list();
		for (String fileName : fileNames) {
			File file = new File(fileName);
			if (file.isDirectory()) {
				System.out.println("Dir: " + fileName);
			} else {
				if (file.isFile()) {
					System.out.println("File: " + fileName);
				}
			}
		}
	}

}
