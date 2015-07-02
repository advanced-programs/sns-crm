package zx.soft.sns.api.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.Set;
import java.util.TreeSet;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.pp.analyzer.ik.lucene.IKAnalyzer;

/**
 * 分词工具类
 *
 * @author wanggang
 *
 */
public class AnalyzerTool {

	private static Logger logger = LoggerFactory.getLogger(AnalyzerTool.class);

	private final Analyzer analyzer;

	public AnalyzerTool() {
		analyzer = new IKAnalyzer(Version.LUCENE_48, true);
	}

	public Set<String> analyzerTextToList(String text) {
		Set<String> result = new TreeSet<>();
		try (TokenStream tokenStream = analyzer.tokenStream(null, new StringReader(text))) {
			CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				result.add(charTermAttribute.toString());
			}
			tokenStream.end();
		} catch (IOException e) {
			logger.error("Exception:{}, StackTrace:{}", e.getMessage(), e.getStackTrace());
			throw new RuntimeException(e);
		}
		return result;
	}

	public void close() {
		if (analyzer != null) {
			analyzer.close();
		}
	}

}
