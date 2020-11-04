import pandas as pd
from alpha_vantage.timeseries import TimeSeries
import matplotlib.pyplot as plt
import time

#LDQW7564ZBSM8GWH
ts = TimeSeries("LDQW7564ZBSM8GWH", output_format="pandas")

target_price = 450

while True:
  data, meta_data = ts.get_intraday(symbol="TSLA", interval="1min", outputsize="full")

  closing_data = data["4. close"]
  recent_price = closing_data[0]
  if recent_price >= target_price:
    print("ALERT | STOCK IS AT: ", recent_price, " | ALERT")
    break
  else:
    print("PRICE AT: ", recent_price)
    time.sleep(5)