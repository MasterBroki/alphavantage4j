package co.alphavantage.output.technicalindicators;

import co.alphavantage.output.JsonParser;
import co.alphavantage.output.technicalindicators.data.IndicatorData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Representation of the parabolic SAR (SAR) response from api.
 *
 * @see TechnicalIndicatorResponse
 */
public class SAR extends TechnicalIndicatorResponse<IndicatorData> {

  private SAR(final Map<String, String> metaData,
              final List<IndicatorData> indicators) {
    super(metaData, indicators);
  }

  /**
   * Creates {@code SAR} instance from json.
   *
   * @param json string to parse
   * @return SAR instance
   */
  public static SAR from(String json) {
    Parser parser = new Parser();
    return parser.parseJson(json);
  }

  /**
   * Helper class for parsing json to {@code SAR}.
   *
   * @see TechnicalIndicatorParser
   * @see JsonParser
   */
  private static class Parser extends TechnicalIndicatorParser<SAR> {

    @Override
    String getIndicatorKey() {
      return "Technical Analysis: SAR";
    }

    @Override
    SAR resolve(Map<String, String> metaData,
                Map<String, Map<String, String>> indicatorData) {
      List<IndicatorData> indicators = new ArrayList<>();
      indicatorData.forEach((key, values) -> indicators.add(new IndicatorData(
              LocalDateTime.parse(key, DATE_WITH_SIMPLE_TIME_FORMAT),
              Double.parseDouble(values.get("SAR"))
      )));
      return new SAR(metaData, indicators);
    }
  }
}