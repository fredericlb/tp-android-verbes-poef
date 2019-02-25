package fr.epsi.verbes;

public class Verbe {
  String baseVerbale;
  String traduction;
  String preterit;
  String participePasse;

  public Verbe(String baseVerbale, String traduction, String preterit, String participePasse) {
    this.baseVerbale = baseVerbale;
    this.traduction = traduction;
    this.preterit = preterit;
    this.participePasse = participePasse;
  }
}
