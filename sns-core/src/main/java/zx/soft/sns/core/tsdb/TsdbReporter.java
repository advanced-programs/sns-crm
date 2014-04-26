package zx.soft.sns.core.tsdb;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TsdbReporter {

	private static final Logger logger = LoggerFactory.getLogger(TsdbReporter.class);

	private final List<Reportable> reportables = new ArrayList<Reportable>();

	private final String host;

	private final int port;

	public TsdbReporter(String tsdbHost, int tsdbPort) {
		logger.info("TSDB host={}, port={}", tsdbHost, tsdbPort);
		this.host = tsdbHost;
		this.port = tsdbPort;
		Timer timer = new Timer("TsdbReporter", true);
		long now = System.currentTimeMillis();
		long start = now - now % (1000 * 60 * 2) + 1000 * 60 * 2; // 下一个两分钟的整点时间
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				exec();
			}
		}, new Date(start), 1000 * 60 * 2);
	}

	public void addReport(Reportable report) {
		reportables.add(report);
	}

	void exec() {
		try (Socket socket = new Socket(host, port);
				PrintWriter writer = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())));) {
			for (Reportable report : reportables) {
				for (Tsdb tsdb : report.report()) {
					writer.println(tsdb.serialize());
				}
			}
			writer.flush();
		} catch (Exception e) {
			logger.warn("report to TSDB exception, host={}, port={}, errorMsg={}", host, port, e.getMessage());
		}
	}

}
