package de.sebhn.algorithm.excercise1;

import java.util.concurrent.TimeUnit;

/**
 * Excercise 2 <br>
 * Use -Xss258m to avoid stackOverflowErro
 * 
 * @author manuel
 *
 */
public class StringManipulation {

  private static String STRING_1024_LENGTH =
      "ZE6lS55KaYu4w6sewoLSNB658fZZWYm49AVksTxccx3t64WBbAB6GmGpwBVMwNBjOjne5cfAzPxvUHvtRE2ARY224T3InSHTDngiqN72DLKXIgO6kmpAX6a8JmLSmJMLaaIVj5JH3WEONpwqOtkiagDalr6j2fw5ey7yMW1rLgIOlpsv4LWviujjA5iW2T4lTj1wUQ2bq46AMPRgOUZNEFyxt76tIsIIKELtlAKuTrJLTk97kOhQKrUrTEF8Q7vx4ypGA9xJxQ37OCxs88Q5Q1MXvrr3iZLUzhKmavs6uihfmIpoz2VX4Z0XmEEsQEyAkM0ybyQij57l11OH3pVtYWj4D90RZVZu4NjwUF6VMxUqxy8sZzZrBmba0WegpEzTb87Heez5vn6jJhuv9jmezgXlyfwrq2hwjwP4yH4NC1ueEgiIzrlCEYw9xus2F2rvryt93ojBVl2hWKF7gxAqwUKOxhcVSkb9AmtiQKgvgGNuzfWNuVR1TQ0sZQyHOaHWuD14PYTvWWL58e2ZscSZ3ESO3cW9MsAHRFnaOkEF3Wh8h9KxLo9l3E9kXxvjL1UEE7vNcbU05lcJHB84gpO5BM3J2vUeMDFHVQ3cqj0bAcXEwKNFmgJb0ro2xIfaGqfvbOUKHUx37xbq85gKAJU03LmcwHPRgkatG1sa37IupGv9iB8VTstgR9HAKs3ayOveS1vr4kri9gI65UwEzA5zV5goPsJUCAh4GYRr7Sp29eHFJPZpErOEQJ7ZFVEgu2JkuLcZwJUvk2hJ7kPSWfy4FethN6lXs3EzUVSGfqMzfuN3FyTsaKMVOcJ9Ym1DQbhQSmNFn2KnzXMM0gbOI0pNMaYrX1EgQmNpBlaz9ikBG1S649lbMyoz2wkrELIqz0Mi0l7arA8ZggV3N4yWZpx0Ml3kTHSAiaq5obZklhMSoL4hNgN5Z26hku1PLvCsaleHIASHXQ4nL4snivVYyx9nWYG7vNtexOCLncRU4p5foUWUO1Snjs1y6gKkAgr7KFQa";
  private static String STRING_2048_LENGTH =
      "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";
  private static String STRING_4096_LENGTH =
      "YGk0KJKAIVQfFMRRfZMO7MfETa7YxNkGKsAPs6TvuPGrfkfVQ8EqRTBTnF1CKNFVpSRvFo0zf6OCqjkhigPt2EwzpRqVOV5pC5M4tUcyhKG59vZUYgTxz9e1VMo80NuohxNnrcTwLjpmU6aqU5Het0CwtvfIiGFN5a0SXtSq9boHxUWc8us8BNh6LFIRgcrXJJXtaYHPeCOK0HFDT2oJxuR5HTnHYmPI6C9bobMH40NZbvc8ex0QMT2imFR8WeV2BrbtHIQcn5K6fv5X6p0EkAi958eWn74KaFxmppEfPJZJSFR7R4o98b0KHZi9hmJhyfAlRPxIB4faO93DCZI0lgGRMJSi4XSSJ1mgbSg10Pn2hxqNbKbHZIc65rEAxEpA3ZFOGtHs0nLD35HObq6HX8tmqowyiPCvAxiflK5kHXnb5UzwUD3IXANILQWH3bc6WoLSoUrnUSXZeZKcgHRkcwv6TOocVWhCPJbrADpXcnq0tUIGI0itxyZE4m1ghFgzJpDkT3VusioHZhok3BTnII0oBGsWrFHj5jYFVs78Dnqjmoaf4sECxzRzamL0i0EHtkjkCXQb1LWZUgzMxomj7ybFJ779Qg7fvvLLMXactpE5G3kJmlrP9fDAQZy4z1eF6VyNTrYLr3KwHuvlWSx54qOQWsXmlWNW95a4BQ05shUQREM90B4kBaxcU4gOh1By1w9X8ATwEWk9Wi3KAwCpBTxgFJHAM00eoHDjkejCz4BnFsPFwNxgQ706I8HOycs9fhXzqNQm3SDJRft1L4POT2JBf4ZMmbCzaWZUbtFHvTtAys4FTIVVT9vtr3DTrOWL0NAYqKyE4PUrN5CDDOuTx4li7vgrelC5EPxeL1VBPkCHND0RyobjU3gFBuRrZHse1hwtS20ubgSh5ykv65DFEqFDRisbUq6UTW6bhZPzPbqzyqCm7IbQeWz9JhcbDiqtV1VtvDMGhUDJa2RHUGUthOOvbzWY36VpfRN9VK6SfqgI57Jip6ZyxcDfNWZQIYGDlX7BIFyVsjcjQpxkZt54RR8cmNsBszrTtMB7yrzD2liQPV8VxbsB74GqbzLEAfKPu5bXXYE4McOLr8LHb5cCUoqqe2W4QAB7e0zFSJak6amL1WxiUU4sWmQxHB6waG2XrGWoDwzOUCKYBjbRq1iHomshNKYSasrDWB8QokZl3kLwEWvtxUEFE6MIwa2GNIVhXnluZaFLbMu8xRh7JZ6gMHhb5B0KrZzFUeEYDT0brDeaAxfGT1CgEZab6in4fuotPjnPzymAUQwta91eIfkZnPPxEQZQs8ZKp0j26hkuObJuBFfi1nbHYwOyiIFlSB3sv0UNcAoZi9ObBpATZCsnxuNuPJ5hxVX08Tpj85rWvwhbW9lT5BlHqqF61xgQXiW1x3xSMJKDb8SGeQw6cnPyC3LBu2UVP41QTpqQGb9zNyU3Yrg6yQWqfTigYvfyiMUMBAoOP1oDOlAJE2jJATW993aU53xeS8r0KQoJDH1FQwojheGQDhGl7ThDouiEylCkya0ygBuS3EMAycbevYl5JTA1yD7A6urbn8egh7rvh6gAD3DMT9sReSjWbr6Qocqm9oYfmfBRxzL5n1WqHvgSKOQhTXPoZGZOSom0vNnhWlFNmxLNMHqcrhwDUqXUyhyQC5F2kgQPqW4ql69ihv8wlF0CV8AfbZe3kS4nNHDB5zeDjbNb6A1tGZwrSxbveKSusZKsw9SoY8aIlbrJ6R6vNjR5jyH4g841BaGtJeiBRrKeEVXJb6rUWXIyTVzUL2lUH7Vn8F41ao9BOSQyVSX89YQnEIwxxko8tmRL0lXgmtGlqiw9IG5K9Prr11kkXScvLQ77WppSXqIm2Y1Vc2APb9f44YQlBoIrM7004Zi2RaNGNwgjkxsG8mAX3fnjD8z0z5FqUOpCMLSNN1grQD4RAouNAm4ASCquXnFvNpktAhR0x38T0WYDyAsQoicVXENAQJO2TafFqB20MGGRarZBiqVNHTsw9bbhJ9qwYGQOkq9QPKqXtkyOjyO4KpNH1oyzCG9qr6vtu9i4sZTQ7RgTJR2Z3Vus4awK6gjXiLp8BYVVZ6Vrjm4a3rDasmlgYSYATSGp3KfXWRlaq18IvYsUlGoUuHGX8T2RIRPihI6JJXXiVangWop0T40GkuC1PUwey8Hp7ebDQCiUmLZlYIHnF3teugZ75fzEyLyRzZ9E3H4A2Kqlfw6KCNmXH53i63yzWJDp8gbCH2IgYxzMP15KyUbtTZ01VO6G7us0y0HFKpSPnM9EpewGl2zC1IFZZEXfiYauMHCZKkPpaxJ64zlMVvouAYYXPevngzo5UipAwpcVlE1MFe4Hg5s63sr8gMmiggylgcgHL0yX7yXbGv1qT0rlhS4Yf5otW9E1KM2w7CXbnnYVCS29AZUCg5hWpK8UR2PGqtrCvD7URi13ewhnFDn2muWu6zxxCsTjDhA7mlmn5GaQlh0OVoaqQGvwRSWVNFYWrtFVqju08oRWN84LIqLbYgkJ1OToVqmjkl3IShl5SKAPvtovNRKnCP6DUf5iP9WF27AAKBT3Wuw8y5yshLvizcm66xXF2CYGIaAUwmbMx31MrYsqu2AmvzwWweY1FRJjDVwsCMyR4q8POs5hDj5hUFf17cUlYk9czRwxePfghhQmAj4Mnu3Z2sqQLR44gVbeB0lbmTptQurz6QHfvTJeuHu3N3JTrYQWUw5WhveNYcGqAhCVKIe2nbNk0oqAHXtPQAz9eB6Fq8LZDsZL0m71i7pkfsS9rRD7XFym7cRGMmo58uFj4bqnf3sxgCPGxPLP3rcUY2mpwk4yVPUpMDYpBcoi6pqlWGApZvGTL8R2wpp5PLPleQn24hxVjpcwVcnHiVZBirTzQV9pM2kMGcsAWJvuxgxOHZ9vH82rCht4nXL6Tzi5ksn7NQaA1y2W8BqJzQcOnImpeTbWQJalaMb2CBmStTtwpj43PUUILS2U1lcZB4KCpWpe4IoWkn0zzYZIQs2g3PG7K5SDZMBa4oEWgIEe5qnWwiNZ35laGVDvMZbGT4GDwywYvvRarSobhih8POhW6QXuuvzDssiwTIq6bKxIne8MWHsgDkiLYEHGxYaQFelnGkuAYG5KFg0kY8bPP9iC17r7VCFo9mwqnCsBONSzc409BmunAzQ1lIx491e8rMo1sGyz51NnXIscWa18sYfyP73JoH2qf4NgytLJAKywyKtpwavjITwMNQoOEtJZ6HBkt4pIDEOLPQB5bYQ6IkSnBC5OZ2FiB9f0gECwiV0pbg6sgZMJ8WMTPDaDF6x39EnvzDuIoGoySL0T1bo7k6jMLNIeeRKHFXuX2nP6saVq3qRFhg9te96DkRP7DSpqRvy3XKA2c7TIGjNV8a7N0aSNaBjSzITI608VUVrYK5C6fIODDtmRQ0kfwVz3Q8Sjhq9L9tPhEVfe0zBmVbTq43HW0CWZMVlebjuxOxg9ijlmIiFHtk2kw2H5jLBEspa09QBKtxBYeXzc0XGKKF5b17xO7vV4jvNwMg4U7XFnNGzElZIa4b0wEkwQgjWW9eAnG7EUfUrqz4rrYjuHIOpC0rZGjNfBgzkTazwRm0DAoS8F3yvNP8oqfuF2r5eNX0hNzSkR2eCSxcH9kB9TEhlaU9JfGckLWx8p64OlfgSw0vIwpwp627cJ3JJoF6a2cNyFgF2kiMY6hhkXwKElCnKwSxV0O5U7GOoHXtD02bMINvlJxlLPRMlXZTAF4pgNhNlQSEc5wq4OxhUGopzODXa7Hi54KDBjhHFBaV5JLMskM82H54H4Ie56KIYlkk4n46M7B2DvjAGDc0l7xvhXonLi0son0UqzfpuXD0W9N537s1l4ex21CtBFAzTv5bnVCL3h9OTgAJ4X4yCwiyVXRS9L3YeDFrCtO6xl61ftqWMRug9pz8rNg9BsNwieEYn7jUN2H2Kj2uTAHFGTA2Sv1Ty2eHBGSRG0FN8bXBAaPPVbtqEUKovuuiRu169SfFW3kfNwVAgMypFvGbnhUHuagf7LaMIEXcR3uzS5GfkHJSilGqcRT22B46zinDPzHsUCkNECDwPzbZiY437pkx7g";
  private static String STRING_8192_LENGTH =
      "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";

  public static void main(String[] args) {
    measureReverse(STRING_1024_LENGTH);
    measureReverse(STRING_2048_LENGTH);
    measureReverse(STRING_4096_LENGTH);
    measureReverse(STRING_8192_LENGTH);
    System.exit(0);
  }

  private static void measureReverse(String someString) {
    System.out.println("Length of String: " + someString.length());
    long startRekm = System.nanoTime();
    rekm(someString);
    long durationRekm = System.nanoTime() - startRekm;
    printDuration("Duration Rekm", durationRekm);
    long startRekl = System.nanoTime();
    rekl(someString);
    long durationRekl = System.nanoTime() - startRekl;
    printDuration("Duration Rekl", durationRekl);
  }

  private static void printDuration(String description, long durationInNs) {
    System.out.println(description + " in " + TimeUnit.NANOSECONDS.toMillis(durationInNs) + "ms "
        + " in " + durationInNs + "ns");
  }

  public static String rekm(String s) {
    int m = s.length() / 2;
    return m == 0 ? s : rekm(s.substring(m)) + rekm(s.substring(0, m));
  }

  public static String rekl(String s) {
    return s.length() <= 1 ? s : rekl(s.substring(1)) + s.charAt(0);
  }
}
