package de.damcraft.serverseeker.country;

import de.damcraft.serverseeker.ServerSeeker;
import meteordevelopment.meteorclient.utils.network.MeteorExecutor;

public class Countries {
    public static void init() {
        MeteorExecutor.execute(() -> {
            ServerSeeker.COUNTRY_MAP.put("AF", new Country("Afghanistan", "AF"));
            ServerSeeker.COUNTRY_MAP.put("AX", new Country("Aland Islands", "AX"));
            ServerSeeker.COUNTRY_MAP.put("AL", new Country("Albania", "AL"));
            ServerSeeker.COUNTRY_MAP.put("DZ", new Country("Algeria", "DZ"));
            ServerSeeker.COUNTRY_MAP.put("AS", new Country("American Samoa", "AS"));
            ServerSeeker.COUNTRY_MAP.put("AD", new Country("Andorra", "AD"));
            ServerSeeker.COUNTRY_MAP.put("AO", new Country("Angola", "AO"));
            ServerSeeker.COUNTRY_MAP.put("AI", new Country("Anguilla", "AI"));
            ServerSeeker.COUNTRY_MAP.put("AQ", new Country("Antarctica", "AQ"));
            ServerSeeker.COUNTRY_MAP.put("AG", new Country("Antigua And Barbuda", "AG"));
            ServerSeeker.COUNTRY_MAP.put("AR", new Country("Argentina", "AR"));
            ServerSeeker.COUNTRY_MAP.put("AM", new Country("Armenia", "AM"));
            ServerSeeker.COUNTRY_MAP.put("AW", new Country("Aruba", "AW"));
            ServerSeeker.COUNTRY_MAP.put("AU", new Country("Australia", "AU"));
            ServerSeeker.COUNTRY_MAP.put("AT", new Country("Austria", "AT"));
            ServerSeeker.COUNTRY_MAP.put("AZ", new Country("Azerbaijan", "AZ"));
            ServerSeeker.COUNTRY_MAP.put("BS", new Country("Bahamas", "BS"));
            ServerSeeker.COUNTRY_MAP.put("BH", new Country("Bahrain", "BH"));
            ServerSeeker.COUNTRY_MAP.put("BD", new Country("Bangladesh", "BD"));
            ServerSeeker.COUNTRY_MAP.put("BB", new Country("Barbados", "BB"));
            ServerSeeker.COUNTRY_MAP.put("BY", new Country("Belarus", "BY"));
            ServerSeeker.COUNTRY_MAP.put("BE", new Country("Belgium", "BE"));
            ServerSeeker.COUNTRY_MAP.put("BZ", new Country("Belize", "BZ"));
            ServerSeeker.COUNTRY_MAP.put("BJ", new Country("Benin", "BJ"));
            ServerSeeker.COUNTRY_MAP.put("BM", new Country("Bermuda", "BM"));
            ServerSeeker.COUNTRY_MAP.put("BT", new Country("Bhutan", "BT"));
            ServerSeeker.COUNTRY_MAP.put("BO", new Country("Bolivia", "BO"));
            ServerSeeker.COUNTRY_MAP.put("BA", new Country("Bosnia And Herzegovina", "BA"));
            ServerSeeker.COUNTRY_MAP.put("BW", new Country("Botswana", "BW"));
            ServerSeeker.COUNTRY_MAP.put("BV", new Country("Bouvet Island", "BV"));
            ServerSeeker.COUNTRY_MAP.put("BR", new Country("Brazil", "BR"));
            ServerSeeker.COUNTRY_MAP.put("IO", new Country("British Indian Ocean Territory", "IO"));
            ServerSeeker.COUNTRY_MAP.put("BN", new Country("Brunei Darussalam", "BN"));
            ServerSeeker.COUNTRY_MAP.put("BG", new Country("Bulgaria", "BG"));
            ServerSeeker.COUNTRY_MAP.put("BF", new Country("Burkina Faso", "BF"));
            ServerSeeker.COUNTRY_MAP.put("BI", new Country("Burundi", "BI"));
            ServerSeeker.COUNTRY_MAP.put("KH", new Country("Cambodia", "KH"));
            ServerSeeker.COUNTRY_MAP.put("CM", new Country("Cameroon", "CM"));
            ServerSeeker.COUNTRY_MAP.put("CA", new Country("Canada", "CA"));
            ServerSeeker.COUNTRY_MAP.put("CV", new Country("Cape Verde", "CV"));
            ServerSeeker.COUNTRY_MAP.put("KY", new Country("Cayman Islands", "KY"));
            ServerSeeker.COUNTRY_MAP.put("CF", new Country("Central African Republic", "CF"));
            ServerSeeker.COUNTRY_MAP.put("TD", new Country("Chad", "TD"));
            ServerSeeker.COUNTRY_MAP.put("CL", new Country("Chile", "CL"));
            ServerSeeker.COUNTRY_MAP.put("CN", new Country("China", "CN"));
            ServerSeeker.COUNTRY_MAP.put("CX", new Country("Christmas Island", "CX"));
            ServerSeeker.COUNTRY_MAP.put("CC", new Country("Cocos (Keeling) Islands", "CC"));
            ServerSeeker.COUNTRY_MAP.put("CO", new Country("Colombia", "CO"));
            ServerSeeker.COUNTRY_MAP.put("KM", new Country("Comoros", "KM"));
            ServerSeeker.COUNTRY_MAP.put("CG", new Country("Congo", "CG"));
            ServerSeeker.COUNTRY_MAP.put("CD", new Country("Congo, Democratic Republic", "CD"));
            ServerSeeker.COUNTRY_MAP.put("CK", new Country("Cook Islands", "CK"));
            ServerSeeker.COUNTRY_MAP.put("CR", new Country("Costa Rica", "CR"));
            ServerSeeker.COUNTRY_MAP.put("CI", new Country("Cote D'Ivoire", "CI"));
            ServerSeeker.COUNTRY_MAP.put("HR", new Country("Croatia", "HR"));
            ServerSeeker.COUNTRY_MAP.put("CU", new Country("Cuba", "CU"));
            ServerSeeker.COUNTRY_MAP.put("CY", new Country("Cyprus", "CY"));
            ServerSeeker.COUNTRY_MAP.put("CZ", new Country("Czech Republic", "CZ"));
            ServerSeeker.COUNTRY_MAP.put("DK", new Country("Denmark", "DK"));
            ServerSeeker.COUNTRY_MAP.put("DJ", new Country("Djibouti", "DJ"));
            ServerSeeker.COUNTRY_MAP.put("DM", new Country("Dominica", "DM"));
            ServerSeeker.COUNTRY_MAP.put("DO", new Country("Dominican Republic", "DO"));
            ServerSeeker.COUNTRY_MAP.put("EC", new Country("Ecuador", "EC"));
            ServerSeeker.COUNTRY_MAP.put("EG", new Country("Egypt", "EG"));
            ServerSeeker.COUNTRY_MAP.put("SV", new Country("El Salvador", "SV"));
            ServerSeeker.COUNTRY_MAP.put("GQ", new Country("Equatorial Guinea", "GQ"));
            ServerSeeker.COUNTRY_MAP.put("ER", new Country("Eritrea", "ER"));
            ServerSeeker.COUNTRY_MAP.put("EE", new Country("Estonia", "EE"));
            ServerSeeker.COUNTRY_MAP.put("ET", new Country("Ethiopia", "ET"));
            ServerSeeker.COUNTRY_MAP.put("FK", new Country("Falkland Islands (Malvinas)", "FK"));
            ServerSeeker.COUNTRY_MAP.put("FO", new Country("Faroe Islands", "FO"));
            ServerSeeker.COUNTRY_MAP.put("FJ", new Country("Fiji", "FJ"));
            ServerSeeker.COUNTRY_MAP.put("FI", new Country("Finland", "FI"));
            ServerSeeker.COUNTRY_MAP.put("FR", new Country("France", "FR"));
            ServerSeeker.COUNTRY_MAP.put("GF", new Country("French Guiana", "GF"));
            ServerSeeker.COUNTRY_MAP.put("PF", new Country("French Polynesia", "PF"));
            ServerSeeker.COUNTRY_MAP.put("TF", new Country("French Southern Territories", "TF"));
            ServerSeeker.COUNTRY_MAP.put("GA", new Country("Gabon", "GA"));
            ServerSeeker.COUNTRY_MAP.put("GM", new Country("Gambia", "GM"));
            ServerSeeker.COUNTRY_MAP.put("GE", new Country("Georgia", "GE"));
            ServerSeeker.COUNTRY_MAP.put("DE", new Country("Germany", "DE"));
            ServerSeeker.COUNTRY_MAP.put("GH", new Country("Ghana", "GH"));
            ServerSeeker.COUNTRY_MAP.put("GI", new Country("Gibraltar", "GI"));
            ServerSeeker.COUNTRY_MAP.put("GR", new Country("Greece", "GR"));
            ServerSeeker.COUNTRY_MAP.put("GL", new Country("Greenland", "GL"));
            ServerSeeker.COUNTRY_MAP.put("GD", new Country("Grenada", "GD"));
            ServerSeeker.COUNTRY_MAP.put("GP", new Country("Guadeloupe", "GP"));
            ServerSeeker.COUNTRY_MAP.put("GU", new Country("Guam", "GU"));
            ServerSeeker.COUNTRY_MAP.put("GT", new Country("Guatemala", "GT"));
            ServerSeeker.COUNTRY_MAP.put("GG", new Country("Guernsey", "GG"));
            ServerSeeker.COUNTRY_MAP.put("GN", new Country("Guinea", "GN"));
            ServerSeeker.COUNTRY_MAP.put("GW", new Country("Guinea-Bissau", "GW"));
            ServerSeeker.COUNTRY_MAP.put("GY", new Country("Guyana", "GY"));
            ServerSeeker.COUNTRY_MAP.put("HT", new Country("Haiti", "HT"));
            ServerSeeker.COUNTRY_MAP.put("HM", new Country("Heard Island & Mcdonald Islands", "HM"));
            ServerSeeker.COUNTRY_MAP.put("VA", new Country("Holy See (Vatican City State)", "VA"));
            ServerSeeker.COUNTRY_MAP.put("HN", new Country("Honduras", "HN"));
            ServerSeeker.COUNTRY_MAP.put("HK", new Country("Hong Kong", "HK"));
            ServerSeeker.COUNTRY_MAP.put("HU", new Country("Hungary", "HU"));
            ServerSeeker.COUNTRY_MAP.put("IS", new Country("Iceland", "IS"));
            ServerSeeker.COUNTRY_MAP.put("IN", new Country("India", "IN"));
            ServerSeeker.COUNTRY_MAP.put("ID", new Country("Indonesia", "ID"));
            ServerSeeker.COUNTRY_MAP.put("IR", new Country("Iran, Islamic Republic Of", "IR"));
            ServerSeeker.COUNTRY_MAP.put("IQ", new Country("Iraq", "IQ"));
            ServerSeeker.COUNTRY_MAP.put("IE", new Country("Ireland", "IE"));
            ServerSeeker.COUNTRY_MAP.put("IM", new Country("Isle Of Man", "IM"));
            ServerSeeker.COUNTRY_MAP.put("IL", new Country("Israel", "IL"));
            ServerSeeker.COUNTRY_MAP.put("IT", new Country("Italy", "IT"));
            ServerSeeker.COUNTRY_MAP.put("JM", new Country("Jamaica", "JM"));
            ServerSeeker.COUNTRY_MAP.put("JP", new Country("Japan", "JP"));
            ServerSeeker.COUNTRY_MAP.put("JE", new Country("Jersey", "JE"));
            ServerSeeker.COUNTRY_MAP.put("JO", new Country("Jordan", "JO"));
            ServerSeeker.COUNTRY_MAP.put("KZ", new Country("Kazakhstan", "KZ"));
            ServerSeeker.COUNTRY_MAP.put("KE", new Country("Kenya", "KE"));
            ServerSeeker.COUNTRY_MAP.put("KI", new Country("Kiribati", "KI"));
            ServerSeeker.COUNTRY_MAP.put("KR", new Country("Korea", "KR"));
            ServerSeeker.COUNTRY_MAP.put("KP", new Country("North Korea", "KP"));
            ServerSeeker.COUNTRY_MAP.put("KW", new Country("Kuwait", "KW"));
            ServerSeeker.COUNTRY_MAP.put("KG", new Country("Kyrgyzstan", "KG"));
            ServerSeeker.COUNTRY_MAP.put("LA", new Country("Lao People's Democratic Republic", "LA"));
            ServerSeeker.COUNTRY_MAP.put("LV", new Country("Latvia", "LV"));
            ServerSeeker.COUNTRY_MAP.put("LB", new Country("Lebanon", "LB"));
            ServerSeeker.COUNTRY_MAP.put("LS", new Country("Lesotho", "LS"));
            ServerSeeker.COUNTRY_MAP.put("LR", new Country("Liberia", "LR"));
            ServerSeeker.COUNTRY_MAP.put("LY", new Country("Libyan Arab Jamahiriya", "LY"));
            ServerSeeker.COUNTRY_MAP.put("LI", new Country("Liechtenstein", "LI"));
            ServerSeeker.COUNTRY_MAP.put("LT", new Country("Lithuania", "LT"));
            ServerSeeker.COUNTRY_MAP.put("LU", new Country("Luxembourg", "LU"));
            ServerSeeker.COUNTRY_MAP.put("MO", new Country("Macao", "MO"));
            ServerSeeker.COUNTRY_MAP.put("MK", new Country("Macedonia", "MK"));
            ServerSeeker.COUNTRY_MAP.put("MG", new Country("Madagascar", "MG"));
            ServerSeeker.COUNTRY_MAP.put("MW", new Country("Malawi", "MW"));
            ServerSeeker.COUNTRY_MAP.put("MY", new Country("Malaysia", "MY"));
            ServerSeeker.COUNTRY_MAP.put("MV", new Country("Maldives", "MV"));
            ServerSeeker.COUNTRY_MAP.put("ML", new Country("Mali", "ML"));
            ServerSeeker.COUNTRY_MAP.put("MT", new Country("Malta", "MT"));
            ServerSeeker.COUNTRY_MAP.put("MH", new Country("Marshall Islands", "MH"));
            ServerSeeker.COUNTRY_MAP.put("MQ", new Country("Martinique", "MQ"));
            ServerSeeker.COUNTRY_MAP.put("MR", new Country("Mauritania", "MR"));
            ServerSeeker.COUNTRY_MAP.put("MU", new Country("Mauritius", "MU"));
            ServerSeeker.COUNTRY_MAP.put("YT", new Country("Mayotte", "YT"));
            ServerSeeker.COUNTRY_MAP.put("MX", new Country("Mexico", "MX"));
            ServerSeeker.COUNTRY_MAP.put("FM", new Country("Micronesia, Federated States Of", "FM"));
            ServerSeeker.COUNTRY_MAP.put("MD", new Country("Moldova", "MD"));
            ServerSeeker.COUNTRY_MAP.put("MC", new Country("Monaco", "MC"));
            ServerSeeker.COUNTRY_MAP.put("MN", new Country("Mongolia", "MN"));
            ServerSeeker.COUNTRY_MAP.put("ME", new Country("Montenegro", "ME"));
            ServerSeeker.COUNTRY_MAP.put("MS", new Country("Montserrat", "MS"));
            ServerSeeker.COUNTRY_MAP.put("MA", new Country("Morocco", "MA"));
            ServerSeeker.COUNTRY_MAP.put("MZ", new Country("Mozambique", "MZ"));
            ServerSeeker.COUNTRY_MAP.put("MM", new Country("Myanmar", "MM"));
            ServerSeeker.COUNTRY_MAP.put("NA", new Country("Namibia", "NA"));
            ServerSeeker.COUNTRY_MAP.put("NR", new Country("Nauru", "NR"));
            ServerSeeker.COUNTRY_MAP.put("NP", new Country("Nepal", "NP"));
            ServerSeeker.COUNTRY_MAP.put("NL", new Country("Netherlands", "NL"));
            ServerSeeker.COUNTRY_MAP.put("AN", new Country("Netherlands Antilles", "AN"));
            ServerSeeker.COUNTRY_MAP.put("NC", new Country("New Caledonia", "NC"));
            ServerSeeker.COUNTRY_MAP.put("NZ", new Country("New Zealand", "NZ"));
            ServerSeeker.COUNTRY_MAP.put("NI", new Country("Nicaragua", "NI"));
            ServerSeeker.COUNTRY_MAP.put("NE", new Country("Niger", "NE"));
            ServerSeeker.COUNTRY_MAP.put("NG", new Country("Nigeria", "NG"));
            ServerSeeker.COUNTRY_MAP.put("NU", new Country("Niue", "NU"));
            ServerSeeker.COUNTRY_MAP.put("NF", new Country("Norfolk Island", "NF"));
            ServerSeeker.COUNTRY_MAP.put("MP", new Country("Northern Mariana Islands", "MP"));
            ServerSeeker.COUNTRY_MAP.put("NO", new Country("Norway", "NO"));
            ServerSeeker.COUNTRY_MAP.put("OM", new Country("Oman", "OM"));
            ServerSeeker.COUNTRY_MAP.put("PK", new Country("Pakistan", "PK"));
            ServerSeeker.COUNTRY_MAP.put("PW", new Country("Palau", "PW"));
            ServerSeeker.COUNTRY_MAP.put("PS", new Country("Palestinian Territory, Occupied", "PS"));
            ServerSeeker.COUNTRY_MAP.put("PA", new Country("Panama", "PA"));
            ServerSeeker.COUNTRY_MAP.put("PG", new Country("Papua New Guinea", "PG"));
            ServerSeeker.COUNTRY_MAP.put("PY", new Country("Paraguay", "PY"));
            ServerSeeker.COUNTRY_MAP.put("PE", new Country("Peru", "PE"));
            ServerSeeker.COUNTRY_MAP.put("PH", new Country("Philippines", "PH"));
            ServerSeeker.COUNTRY_MAP.put("PN", new Country("Pitcairn", "PN"));
            ServerSeeker.COUNTRY_MAP.put("PL", new Country("Poland", "PL"));
            ServerSeeker.COUNTRY_MAP.put("PT", new Country("Portugal", "PT"));
            ServerSeeker.COUNTRY_MAP.put("PR", new Country("Puerto Rico", "PR"));
            ServerSeeker.COUNTRY_MAP.put("QA", new Country("Qatar", "QA"));
            ServerSeeker.COUNTRY_MAP.put("RE", new Country("Reunion", "RE"));
            ServerSeeker.COUNTRY_MAP.put("RO", new Country("Romania", "RO"));
            ServerSeeker.COUNTRY_MAP.put("RU", new Country("Russian Federation", "RU"));
            ServerSeeker.COUNTRY_MAP.put("RW", new Country("Rwanda", "RW"));
            ServerSeeker.COUNTRY_MAP.put("BL", new Country("Saint Barthelemy", "BL"));
            ServerSeeker.COUNTRY_MAP.put("SH", new Country("Saint Helena", "SH"));
            ServerSeeker.COUNTRY_MAP.put("KN", new Country("Saint Kitts And Nevis", "KN"));
            ServerSeeker.COUNTRY_MAP.put("LC", new Country("Saint Lucia", "LC"));
            ServerSeeker.COUNTRY_MAP.put("MF", new Country("Saint Martin", "MF"));
            ServerSeeker.COUNTRY_MAP.put("PM", new Country("Saint Pierre And Miquelon", "PM"));
            ServerSeeker.COUNTRY_MAP.put("VC", new Country("Saint Vincent And Grenadines", "VC"));
            ServerSeeker.COUNTRY_MAP.put("WS", new Country("Samoa", "WS"));
            ServerSeeker.COUNTRY_MAP.put("SM", new Country("San Marino", "SM"));
            ServerSeeker.COUNTRY_MAP.put("ST", new Country("Sao Tome And Principe", "ST"));
            ServerSeeker.COUNTRY_MAP.put("SA", new Country("Saudi Arabia", "SA"));
            ServerSeeker.COUNTRY_MAP.put("SN", new Country("Senegal", "SN"));
            ServerSeeker.COUNTRY_MAP.put("RS", new Country("Serbia", "RS"));
            ServerSeeker.COUNTRY_MAP.put("SC", new Country("Seychelles", "SC"));
            ServerSeeker.COUNTRY_MAP.put("SL", new Country("Sierra Leone", "SL"));
            ServerSeeker.COUNTRY_MAP.put("SG", new Country("Singapore", "SG"));
            ServerSeeker.COUNTRY_MAP.put("SK", new Country("Slovakia", "SK"));
            ServerSeeker.COUNTRY_MAP.put("SI", new Country("Slovenia", "SI"));
            ServerSeeker.COUNTRY_MAP.put("SB", new Country("Solomon Islands", "SB"));
            ServerSeeker.COUNTRY_MAP.put("SO", new Country("Somalia", "SO"));
            ServerSeeker.COUNTRY_MAP.put("ZA", new Country("South Africa", "ZA"));
            ServerSeeker.COUNTRY_MAP.put("GS", new Country("South Georgia And Sandwich Isl.", "GS"));
            ServerSeeker.COUNTRY_MAP.put("ES", new Country("Spain", "ES"));
            ServerSeeker.COUNTRY_MAP.put("LK", new Country("Sri Lanka", "LK"));
            ServerSeeker.COUNTRY_MAP.put("SD", new Country("Sudan", "SD"));
            ServerSeeker.COUNTRY_MAP.put("SR", new Country("Suriname", "SR"));
            ServerSeeker.COUNTRY_MAP.put("SJ", new Country("Svalbard And Jan Mayen", "SJ"));
            ServerSeeker.COUNTRY_MAP.put("SZ", new Country("Swaziland", "SZ"));
            ServerSeeker.COUNTRY_MAP.put("SE", new Country("Sweden", "SE"));
            ServerSeeker.COUNTRY_MAP.put("CH", new Country("Switzerland", "CH"));
            ServerSeeker.COUNTRY_MAP.put("SY", new Country("Syrian Arab Republic", "SY"));
            ServerSeeker.COUNTRY_MAP.put("TW", new Country("Taiwan", "TW"));
            ServerSeeker.COUNTRY_MAP.put("TJ", new Country("Tajikistan", "TJ"));
            ServerSeeker.COUNTRY_MAP.put("TZ", new Country("Tanzania", "TZ"));
            ServerSeeker.COUNTRY_MAP.put("TH", new Country("Thailand", "TH"));
            ServerSeeker.COUNTRY_MAP.put("TL", new Country("Timor-Leste", "TL"));
            ServerSeeker.COUNTRY_MAP.put("TG", new Country("Togo", "TG"));
            ServerSeeker.COUNTRY_MAP.put("TK", new Country("Tokelau", "TK"));
            ServerSeeker.COUNTRY_MAP.put("TO", new Country("Tonga", "TO"));
            ServerSeeker.COUNTRY_MAP.put("TT", new Country("Trinidad And Tobago", "TT"));
            ServerSeeker.COUNTRY_MAP.put("TN", new Country("Tunisia", "TN"));
            ServerSeeker.COUNTRY_MAP.put("TR", new Country("Turkey", "TR"));
            ServerSeeker.COUNTRY_MAP.put("TM", new Country("Turkmenistan", "TM"));
            ServerSeeker.COUNTRY_MAP.put("TC", new Country("Turks And Caicos Islands", "TC"));
            ServerSeeker.COUNTRY_MAP.put("TV", new Country("Tuvalu", "TV"));
            ServerSeeker.COUNTRY_MAP.put("UG", new Country("Uganda", "UG"));
            ServerSeeker.COUNTRY_MAP.put("UA", new Country("Ukraine", "UA"));
            ServerSeeker.COUNTRY_MAP.put("AE", new Country("United Arab Emirates", "AE"));
            ServerSeeker.COUNTRY_MAP.put("GB", new Country("United Kingdom", "GB"));
            ServerSeeker.COUNTRY_MAP.put("US", new Country("United States", "US"));
            ServerSeeker.COUNTRY_MAP.put("UM", new Country("United States Outlying Islands", "UM"));
            ServerSeeker.COUNTRY_MAP.put("UY", new Country("Uruguay", "UY"));
            ServerSeeker.COUNTRY_MAP.put("UZ", new Country("Uzbekistan", "UZ"));
            ServerSeeker.COUNTRY_MAP.put("VU", new Country("Vanuatu", "VU"));
            ServerSeeker.COUNTRY_MAP.put("VE", new Country("Venezuela", "VE"));
            ServerSeeker.COUNTRY_MAP.put("VN", new Country("Vietnam", "VN"));
            ServerSeeker.COUNTRY_MAP.put("VG", new Country("Virgin Islands, British", "VG"));
            ServerSeeker.COUNTRY_MAP.put("VI", new Country("Virgin Islands, U.S.", "VI"));
            ServerSeeker.COUNTRY_MAP.put("WF", new Country("Wallis And Futuna", "WF"));
            ServerSeeker.COUNTRY_MAP.put("EH", new Country("Western Sahara", "EH"));
            ServerSeeker.COUNTRY_MAP.put("YE", new Country("Yemen", "YE"));
            ServerSeeker.COUNTRY_MAP.put("ZM", new Country("Zambia", "ZM"));
            ServerSeeker.COUNTRY_MAP.put("ZW", new Country("Zimbabwe", "ZW"));
        });
    }
}
