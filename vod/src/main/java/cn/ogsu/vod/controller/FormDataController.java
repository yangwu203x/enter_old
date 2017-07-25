package cn.ogsu.vod.controller;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.ogsu.vod.util.upload.IoUtil;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
@Controller
public class FormDataController{
	static final String FILE_FIELD = "file";
	public static final int BUFFER_LENGTH = 10485760;
	static final int MAX_FILE_SIZE = 104857600;
	@RequestMapping("/fd")
	public void formData(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("utf8");
		PrintWriter writer = resp.getWriter();
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (!isMultipart) {
			writer.println("ERROR: It's not Multipart form.");
			return;
		}
		JSONObject json = new JSONObject();
		long start = 0L;
		boolean success = true;
		String message = "";
		ServletFileUpload upload = new ServletFileUpload();
		InputStream in = null;
		String token = null;
		try {
			FileItemIterator iter = upload.getItemIterator(req);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();
				in = item.openStream();
				if (item.isFormField()) {
					String value = Streams.asString(in);
					if ("token".equals(name)) {
						token = value;
					}
				} else {
					String fileName = item.getName();
					start = IoUtil.streaming(in, token, fileName);
				}
			}
		} catch (FileUploadException fne) {
			success = false;
			message = "Error: " + fne.getLocalizedMessage();
		} finally {
			try {
				if (success)
					json.put("start", start);
				json.put("success", success);
				json.put("message", message);
			} catch (JSONException localJSONException1) {
			}
			writer.write(json.toString());
			IoUtil.close(in);
			IoUtil.close(writer);
		}
	}
}