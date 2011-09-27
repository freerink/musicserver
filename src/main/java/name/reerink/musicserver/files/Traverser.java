package name.reerink.musicserver.files;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Traverse a directory structure. The traverse method calls onFolder, onFile or
 * onError where appropriate.
 * 
 * @author freerink
 * 
 */
public class Traverser {

	private Queue<File> queue = new ConcurrentLinkedQueue<File>();

	private Callback callback;

	private File directory;
	
	public File getDirectory() {
		return directory;
	}

	public Traverser() {
	}

	public void setDirectory(File directory) {
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException("Not a folder: "
					+ directory.getAbsolutePath());
		}
		this.directory = directory;
		this.queue.add(directory);
	}

	/**
	 * Traverse the structure calling onFolder, onFile or onError on the
	 * Callback.
	 * 
	 */
	public void traverse() {
		File work;
		while ((work = this.queue.poll()) != null) {
			if (work.isDirectory()) {
				File[] files = work.listFiles();
				for (File file : files) {
					if (file.isDirectory()) {
						// System.out.println("Dir: " + file.getPath());
						getCallback().onFolder(file);
						this.queue.add(file);
					} else {
						if (file.isFile()) {
							// System.out.println("File: " + file.getPath());
							getCallback().onFile(file);
						} else {
							// System.out.println("Error, what is: " +
							// file.getPath());
							getCallback().onError(file);
						}
					}
				}
			}
		}
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	public Callback getCallback() {
		return callback;
	}

}
