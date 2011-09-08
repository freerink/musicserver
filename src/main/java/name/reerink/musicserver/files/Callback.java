package name.reerink.musicserver.files;

import java.io.File;

public interface Callback {
	
	void onFile(File file);
	
	void onFolder(File dir);
	
	void onError(File thing);
}
