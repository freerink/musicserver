package name.reerink.musicserver.misc;

import java.io.File;

import name.reerink.musicserver.files.Callback;

public class CallbackDefaultImpl implements Callback {

	private int files = 0;
	private int folders = 0;
	private int errors = 0;

	private boolean printFiles = false;
	private boolean printFolders = false;

	public String toString() {
		return "{files=" + files + ", folder=" + folders + ", errors=" + errors
				+ "}";
	}

	public int getFiles() {
		return files;
	}

	public int getFolders() {
		return folders;
	}

	public int getErrors() {
		return errors;
	}

	public void onFile(File file) {
		if (this.printFiles) {
			System.out.println("** File: " + file.getPath());
		}
		this.files++;
	}

	public void onFolder(File dir) {
		if (this.printFolders) {
			System.out.println("** Dir: " + dir.getPath());
		}
		this.folders++;
	}

	public void onError(File thing) {
		System.out.println("** What is this: " + thing.getPath());
		this.errors++;
	}

}
