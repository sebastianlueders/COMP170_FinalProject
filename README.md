# COMP170_FinalProject


# Currency Exchange Program

 **Description:**

This program is used to obtain a fair value quote on a currency swap between two different currencies. By default, this program supports:

* United Arab Emirates Dirham (AED) 
* Cardano (ADA)
* Argentine Peso (ARS) 
* Australian Dollar (AUD) 
* Bulgarian Lev (BGN) 
* Bahraini Dinar (BHD) 
* Binance (BNB)
* Brunei Dollar (BND) 
* Brazilian Real (BRL) 
* Bitcoin (BTC)
* Botswana Pula (BWP) 
* Canadian Dollar (CAD) 
* Swiss Franc (CHF) 
* Chilean Peso (CLP) 
* Colombian Peso (COP) 
* Czech Koruna (CZK) 
* Danish Krone (DKK) 
* Polkadot (DOT)
* Ethereum (ETH)
* Euro (EUR) 
* British Pound Sterling (GBP) 
* Hong Kong Dollar (HKD) 
* Hungarian Forint (HUF) 
* Indonesian Rupiah (IDR) 
* Israeli New Shekel (ILS) 
* Indian Rupee (INR) 
* Iranian Rial (IRR) 
* Icelandic Króna (ISK) 
* Japanese Yen (JPY) 
* South Korean Won (KRW) 
* Kuwaiti Dinar (KWD) 
* Kazakhstani Tenge (KZT) 
* Sri Lankan Rupee (LKR) 
* Libyan Dinar (LYD) 
* Mauritian Rupee (MUR) 
* Mexican Peso (MXN) 
* Malaysian Ringgit (MYR) 
* Norwegian Krone (NOK) 
* Nepalese Rupee (NPR) 
* New Zealand Dollar (NZD) 
* Omani Rial (OMR) 
* Philippine Peso (PHP) 
* Pakistani Rupee (PKR) 
* Polish Złoty (PLN) 
* Qatari Riyal (QAR) 
* Renminbi/Chinese Yuan (RMB) 
* Romanian Leu (RON) 
* Russian Ruble (RUB) 
* Saudi Riyal (SAR) 
* Swedish Krona (SEK) 
* Singapore Dollar (SGD) 
* Solana (SGD)
* Thai Baht (THB) 
* Toncoin (TON)
* Turkish Lira (TRY) 
* Tron (TRX)
* Trinidad and Tobago Dollar (TTD) 
* New Taiwan Dollar (TWD) 
* Venezuelan Bolívar (VED) 
* XRP (XRP)
* South African Rand (ZAR)


Since exchange rates are stored locally in this program, users must update the exchange rates manually. This can be done through the terminal or manually by editing the exrates document.

 **Pseudocode:**

 * Welcome user through console messaging
 * Prompt user for base currency
 * Prompt user for currency they want to exchange to
 * Inform the user when the last exchange rate update was and to check if the exchange rate matches the existing rate
 * Allow user to update the exchange rate with the latest value (if user is exchanging through USD, need both base/USD & x/USD for update)
 * If necessary, find a conversion rate given the x/USD pairings in the exrates.txt file
 * Convert the amount they want to exchange into the fair value amount of their wanted currency
 * Locate the unicode value for the currency they want to exchange to
 * Approximate the fee of the transaction based on typical exchange booth fees
 * Returns the range of "accpetable" exchange values for the currency they are looking to exchange to
