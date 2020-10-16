import pandas
from alpha_vantage.timeseries import TimeSeries
import sys

#
ts = TimeSeries("", output_format='pandas')
values, columns = ts.get_intraday(symbol='TSLA', interval='1min')

print(values)

import matplotlib.pyplot as plt
values['1. open'].plot()
plt.show()
