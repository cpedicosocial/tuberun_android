package com.papagiannis.tuberun;

import java.util.ArrayList;
import java.util.HashMap;



public class StationDetails {
	private static ArrayList<String> resultsTube = null;
    static public ArrayList<String> FetchTubeStationsClaims()
    {
        if (resultsTube != null) return resultsTube;
        ArrayList<String> results = new ArrayList<String>(277);
        results.add("Acton Town");
        results.add("Aldgate");
        results.add("Alperton");
        results.add("Amersham");
        results.add("Angel");
        results.add("Archway");
        results.add("Arnos Grove");
        results.add("Arsenal");
        results.add("Baker Street");
        results.add("Balham");
        results.add("Bank");
        results.add("Barbican");
        results.add("Barking");
        results.add("Barkingside");
        results.add("Barons Court");
        results.add("Bayswater");
        results.add("Becontree");
        results.add("Belsize Park");
        results.add("Bermondsey");
        results.add("Bethnal Green");
        results.add("Blackfriars");
        results.add("Blackhorse Road");
        results.add("Bond Street");
        results.add("Borough");
        results.add("Boston Manor");
        results.add("Bounds Green");
        results.add("Bow Road");
        results.add("Brent Cross");
        results.add("Brixton");
        results.add("Bromley-by-Bow");
        results.add("Buckhurst Hill");
        results.add("Burnt Oak");
        results.add("Caledonian Road");
        results.add("Camden Town");
        results.add("Canada Water");
        results.add("Canary Wharf");
        results.add("Canning Town");
        results.add("Cannon Street");
        results.add("Canons Park");
        results.add("Chalfont & Latimer");
        results.add("Chalk Farm");
        results.add("Chancery Lane");
        results.add("Charing Cross");
        results.add("Chesham");
        results.add("Chigwell");
        results.add("Chiswick Park");
        results.add("Chorleywood");
        results.add("Clapham Common");
        results.add("Clapham North");
        results.add("Clapham South");
        results.add("Cockfosters");
        results.add("Colindale");
        results.add("Colliers Wood");
        results.add("Covent Garden");
        results.add("Croxley");
        results.add("Dagenham East");
        results.add("Dagenham Heathway");
        results.add("Debden");
        results.add("Dollis Hill");
        results.add("Ealing Broadway");
        results.add("Ealing Common");
        results.add("Earl's Court");
        results.add("East Acton");
        results.add("East Finchley");
        results.add("East Ham");
        results.add("East Putney");
        results.add("Eastcote");
        results.add("Edgware");
        results.add("Edgware Road (Bak)");
        results.add("Edgware Road (Cir)");
        results.add("Elephant & Castle");
        results.add("Elm Park");
        results.add("Embankment");
        results.add("Epping");
        results.add("Euston");
        results.add("Euston Square");
        results.add("Fairlop");
        results.add("Farringdon");
        results.add("Finchley Central");
        results.add("Finchley Road");
        results.add("Finsbury Park");
        results.add("Fulham Broadway");
        results.add("Gants Hill");
        results.add("Gloucester Road");
        results.add("Golders Green");
        results.add("Goldhawk Road");
        results.add("Goodge Street");
        results.add("Grange Hill");
        results.add("Great Portland Street");
        results.add("Green Park");
        results.add("Greenford");
        results.add("Gunnersbury");
        results.add("Hainault");
        results.add("Hammersmith (Dis)");
        results.add("Hammersmith (H&C)");
        results.add("Hampstead");
        results.add("Hanger Lane");
        results.add("Harlesden");
        results.add("Harrow & Wealdstone");
        results.add("Harrow-on-the-Hill");
        results.add("Hatton Cross");
        results.add("Heathrow Terminal 4");
        results.add("Heathrow Terminal 5");
        results.add("Heathrow Terminals 123");
        results.add("Hendon Central");
        results.add("High Barnet");
        results.add("High Street Kensington");
        results.add("Highbury & Islington");
        results.add("Highgate");
        results.add("Hillingdon");
        results.add("Holborn");
        results.add("Holland Park");
        results.add("Holloway Road");
        results.add("Hornchurch");
        results.add("Hounslow Central");
        results.add("Hounslow East");
        results.add("Hounslow West");
        results.add("Hyde Park Corner");
        results.add("Ickenham");
        results.add("Kennington");
        results.add("Kensal Green");
        results.add("Kensington (Olympia)");
        results.add("Kentish Town");
        results.add("Kenton");
        results.add("Kew Gardens");
        results.add("Kilbur");
        results.add("Kilburn Park");
        results.add("King's Cross St. Pancras");
        results.add("Kingsbury");
        results.add("Knightsbridge");
        results.add("Ladbroke Grove");
        results.add("Lambeth North");
        results.add("Lancaster Gate");
        results.add("Latimer Road");
        results.add("Leicester Square");
        results.add("Leyton");
        results.add("Leytonstone");
        results.add("Liverpool Street");
        results.add("London Bridge");
        results.add("Loughton");
        results.add("Maida Vale");
        results.add("Manor House");
        results.add("Mansion House");
        results.add("Marble Arch");
        results.add("Marylebone");
        results.add("Mile End");
        results.add("Mill Hill East");
        results.add("Monument");
        results.add("Moor Park");
        results.add("Moorgate");
        results.add("Morden");
        results.add("Moor Park");
        results.add("Moorgate");
        results.add("Morden");
        results.add("Mornington Crescent");
        results.add("Neasden");
        results.add("New Cross");
        results.add("New Cross Gate");
        results.add("Newbury Park");
        results.add("North Acton");
        results.add("North Ealing");
        results.add("North Greenwich");
        results.add("North Harrow");
        results.add("North Wembley");
        results.add("Northfields");
        results.add("Northolt");
        results.add("Northwick Park");
        results.add("Northwood");
        results.add("Northwood Hills");
        results.add("Notting Hill Gate");
        results.add("Oakwood");
        results.add("Old Street");
        results.add("Osterley");
        results.add("Oval");
        results.add("Oxford Circus");
        results.add("PAddington");
        results.add("Park Royal");
        results.add("Parsons Green");
        results.add("Perivale");
        results.add("Piccadilly Circus");
        results.add("Pimlico");
        results.add("Pinner");
        results.add("Plaistow");
        results.add("Preston Road");
        results.add("Putney Bridge");
        results.add("Queen's Park");
        results.add("Queensbury");
        results.add("Queensway");
        results.add("Ravenscourt Park");
        results.add("Rayners Lane");
        results.add("Redbridge");
        results.add("Regent's Park");
        results.add("Richmond");
        results.add("Rickmansworth");
        results.add("Roding Valley");
        results.add("Rotherhithe");
        results.add("Royal Oak");
        results.add("Ruislip");
        results.add("Ruislip Gardens");
        results.add("Ruislip Manor");
        results.add("Russell Square");
        results.add("Seven Sisters");
        results.add("Shadwell");
        results.add("Shepherd's Bush (Cen)");
        results.add("Shepherd's Bush (H&C)");
        results.add("Shepherd's Bush Market");
        results.add("Sloane Square");
        results.add("Snaresbrook");
        results.add("South Ealing");
        results.add("South Harrow");
        results.add("South Kensington");
        results.add("South Kenton");
        results.add("South Ruislip");
        results.add("South Wimbledon");
        results.add("South Woodford");
        results.add("Southfields");
        results.add("Southgate");
        results.add("Southwark");
        results.add("St. James's Park");
        results.add("St. John's Wood");
        results.add("St. Paul's");
        results.add("Stamford Brook");
        results.add("Stanmore");
        results.add("Stepney Green");
        results.add("Stockwell");
        results.add("Stonebridge Park");
        results.add("Stratford");
        results.add("Sudbury Hill");
        results.add("Sudbury Town");
        results.add("Surrey Quays");
        results.add("Swiss Cottage");
        results.add("Temple");
        results.add("Theydon Bois");
        results.add("Tooting Bec");
        results.add("Tooting Broadway");
        results.add("Tottenham Court Road");
        results.add("Tottenham Hale");
        results.add("Totteridge & Whetstone");
        results.add("Tower Hill");
        results.add("Tufnell Park");
        results.add("Turnham Green");
        results.add("Turnpike Lane");
        results.add("Upminster");
        results.add("Upminster Bridge");
        results.add("Upney");
        results.add("Upton Park");
        results.add("Uxbridge");
        results.add("Vauxhall");
        results.add("Victoria");
        results.add("Walthamstow Central");
        results.add("Wanstead");
        results.add("Wapping");
        results.add("Warren Street");
        results.add("Warwick Avenue");
        results.add("Waterloo");
        results.add("Watford");
        results.add("Wembley Central");
        results.add("Wembley Park");
        results.add("West Acton");
        results.add("West Brompton");
        results.add("West Finchley");
        results.add("West Ham");
        results.add("West Hampstead");
        results.add("West Harrow");
        results.add("West Kensington");
        results.add("West Ruislip");
        results.add("Westbourne Park");
        results.add("Westminster");
        results.add("White City");
        results.add("Whitechapel");
        results.add("Willesden Green");
        results.add("Willesden Junction");
        results.add("Wimbledon");
        results.add("Wimbledon Park");
        results.add("Wood Green");
        results.add("Wood Lane");
        results.add("Woodford");
        results.add("Woodside Park");
        resultsTube = results;
        return results;
    }
    private static ArrayList<String> resultsDLR = null;
    public static ArrayList<String> FetchDLRStationsClaims()
    {
        if (resultsDLR != null) return resultsDLR;
        ArrayList<String> results = new ArrayList<String>(40);
        results.add("All Saints");
        results.add("Bank");
        results.add("Beckton");
        results.add("Beckton Park");
        results.add("Blackwall");
        results.add("Bow Church");
        results.add("Canary Wharf");
        results.add("Canning Town");
        results.add("Crossharbour");
        results.add("Custom House");
        results.add("Cutty Sark");
        results.add("Cyprus");
        results.add("Deptford Bridge");
        results.add("Devons Road");
        results.add("East India");
        results.add("Elverson Road");
        results.add("Gallions Reach");
        results.add("Greenwich");
        results.add("Heron Quays");
        results.add("Island Gardens");
        results.add("King George V");
        results.add("Langdon Park");
        results.add("Lewisham");
        results.add("Limehouse");
        results.add("London City Airport");
        results.add("Mudchute");
        results.add("Pontoon Dock");
        results.add("Poplar");
        results.add("Prince Regent");
        results.add("Pudding Mill Lane");
        results.add("Royal Albert");
        results.add("Royal Victoria");
        results.add("Shadwell");
        results.add("South Quay");
        results.add("Stratford");
        results.add("Tower Gateway");
        results.add("Westferry");
        results.add("West India Quay");
        results.add("West Silvertown");
        results.add("Woolwich Arsenal");
        resultsDLR = results;
        return results;
    }
    private static ArrayList<String> resultsOverground = null;
    public static ArrayList<String> FetchOvergroundStationsClaims()
    {
        if (resultsOverground != null) return resultsOverground;
        ArrayList<String> results = new ArrayList<String>(90);
        results.add("Acton Central");
        results.add("Anerley");
        results.add("Barking");
        results.add("Blackhorse Road");
        results.add("Brockley");
        results.add("Brondesbury");
        results.add("Brondesbury Park");
        results.add("Bushey");
        results.add("Caledonian Road and Barnsbury");
        results.add("Camden Road");
        results.add("Canada Water");
        results.add("Canonbury");
        results.add("Carpenders Park");
        results.add("Clapham Junction");
        results.add("Crouch Hill");
        results.add("Crystal Palace");
        results.add("Dalston Junction");
        results.add("Dalston Kingsland");
        results.add("Euston");
        results.add("Finchley Road and Frognal");
        results.add("Forest Hill");
        results.add("Gospel Oak");
        results.add("Gunnersbury");
        results.add("Hackney Central");
        results.add("Hackney Wick");
        results.add("Haggerston");
        results.add("Hampstead Heath");
        results.add("Harlesden");
        results.add("Harringay Green Lanes");
        results.add("Harrow & Wealdstone");
        results.add("Hatch End");
        results.add("Headstone Lane");
        results.add("Highbury & Islington");
        results.add("Homerton");
        results.add("Honor Oak Park");
        results.add("Hoxton");
        results.add("Imperial Wharf");
        results.add("Kensal Green");
        results.add("Kensal Rise");
        results.add("Kensington Olympia");
        results.add("Kentish Town West");
        results.add("Kenton");
        results.add("Kew Gardens");
        results.add("Kilburn High Road");
        results.add("Leyton Midland Road");
        results.add("Leytonstone High Road");
        results.add("New Cross");
        results.add("New Cross Gate");
        results.add("North Wembley");
        results.add("Norwood Junction");
        results.add("Penge West");
        results.add("Queen's Park");
        results.add("Richmond");
        results.add("Rotherhithe");
        results.add("Shadwell");
        results.add("Shepherd's Bush");
        results.add("Shoreditch High Street");
        results.add("South Acton");
        results.add("South Hampstead");
        results.add("South Kenton");
        results.add("South Tottenham");
        results.add("Stonebridge Park");
        results.add("Stratford");
        results.add("Surrey Quays");
        results.add("Sydenham");
        results.add("Upper Holloway");
        results.add("Walthamstow Queens Road");
        results.add("Wanstead Park");
        results.add("Wapping");
        results.add("Watford High Street");
        results.add("Watford Junction");
        results.add("Wembley Central");
        results.add("West Brompton");
        results.add("West Croydon");
        results.add("West Hampstead");
        results.add("Whitechapel");
        results.add("Willesden Junction");
        results.add("Woodgrange Park");
        resultsOverground = results;
        return results;
    }
    static public HashMap<String, String> FetchStations(LineType line)
    {
        HashMap<String, String> res = new HashMap<String, String>();
        switch (line)
        {
            case DLR:
                res.put("All Saints" , "als");
                res.put("Bank","ban");
                res.put("Beckton","bec");
                res.put("Beckton Park","bep");
                res.put("Blackwall","bla");
                res.put("Bow Church","boc");
                res.put("Canary Wharf","caw");
                res.put("Canning Town","cat");
                res.put("Crossharbour","cro");
                res.put("Custom House","cuh");
                res.put("Cutty Sark","cus");
                res.put("Cyprus","cyp");
                res.put("Deptford Bridge","deb");
                res.put("Devons Road","der");
                res.put("East India","eai");
                res.put("Elverson Road","elr");
                res.put("Gallions Reach","gar");
                res.put("Greenwich","gre");
                res.put("Heron Quays","heq");
                res.put("Island Gardens","isg");
                res.put("King George V","kgv");
                res.put("Langdon Park","lap");
                res.put("Lewisham","lew");
                res.put("Limehouse","lim");
                res.put("London City Airport","lca");
                res.put("Mudchute","mud");
                res.put("Poplar","pop");
                res.put("Pontoon Dock","pdk");
                res.put("Prince Regent","prr");
                res.put("Pudding Mill Lane","pml");
                res.put("Royal Albert","roa");
                res.put("Royal Victoria","rov");
                res.put("Shadwell","sha");
                res.put("South Quay","soq");
                res.put("Stratford","str");
                res.put("Tower Gateway","tog");
                res.put("Westferry","wes");
                res.put("West India Quay","wiq");
                res.put("West Silvertown","wst");
                res.put("Woolwich Arsenal","woa");
                break;
            case PICACIDILY:
                res.put("Acton Town","ACT");
                res.put("Alperton","ALP");
                res.put("Gloucester Road","GRD");
                res.put("Finsbury Park","FPK");
                res.put("Eastcote","ETE");
                res.put("Earls Court","ECT");
                res.put("Ealing Common","ECM");
                res.put("Covent Garden","COV");
                res.put("Cockfosters","CFS");
                res.put("Caledonian Road","CRD");
                res.put("Bounds Green","BGR");
                res.put("Boston Manor","BOS");
                res.put("Barons Court","BCT");
                res.put("Arsenal","ARL");
                res.put("Arnos Grove","AGR");
                res.put("Green Park","GPK");
                res.put("Hammersmith","HMD");
                res.put("Hatton Cross","HTX");
                res.put("Heathrow T4","HRF");
                res.put("Heathrow T5","HRV");
                res.put("Heathrow T123","HRC");
                res.put("Hillingdon","HDN");
                res.put("Holborn","HOL");
                res.put("Holloway Road","HRD");
                res.put("Hounslow Central","HNC");
                res.put("Hounslow East","HNE");
                res.put("Hounslow West","HNW");
                res.put("Hyde Park Corner","HPC");
                res.put("Ickenham","ICK");
                res.put("King's Cross St. Pancras","KXX");
                res.put("Knightsbridge","KNB");
                res.put("Leicester Square","LSQ");
                res.put("Manor House","MNR");
                res.put("North Ealing","NEL");
                res.put("Northfields","NFD");
                res.put("Oakwood","OAK");
                res.put("Osterley","OST");
                res.put("Park Royal","PRY");
                res.put("Piccadilly Circus","PIC");
                res.put("Rayners Lane","RLN");
                res.put("Ruislip","RUI");
                res.put("Ruislip Manor","RUM");
                res.put("Russell Square","RSQ");
                res.put("South Ealing","SEL");
                res.put("South Harrow","SHR");
                res.put("South Kensington","SKN");
                res.put("Southgate","SGT");
                res.put("Sudbury Hill","SHL");
                res.put("Sudbury Town","STN");
                res.put("Turnham Green","TGR");
                res.put("Turnpike Lane","TPL");
                res.put("Uxbridge","UXB");
                res.put("Wood Green","WGN");
                break;

            case BAKERLOO:
                res.put("Baker Street","BST");
                res.put("Charing Cross","CHX");
                res.put("Edgware Road (Bakerloo)","ERB");
                res.put("Elephant & Castle","ELE");
                res.put("Embankment","EMB");
                res.put("Harlesden","HSD");
                res.put("Harrow & Wealdstone","HAW");
                res.put("Kensal Green","KGN");
                res.put("Kenton","KNT");
                res.put("Kilburn Park","KPK");
                res.put("Lambeth North","LAM");
                res.put("Maida Vale","MDV");
                res.put("Marylebone","MYB");
                res.put("North Wembley","NWM");
                res.put("Oxford Circus","OXC");
                res.put("Paddington","PAD");
                res.put("Piccadilly Circus","PIC");
                res.put("Queen's Park","QPK");
                res.put("Regent's Park","RPK");
                res.put("South Kenton","SKT");
                res.put("Stonebridge Park","SPK");
                res.put("Warwick Avenue","WAR");
                res.put("Waterloo","WLO");
                res.put("Wembley Central","WEM");
                res.put("Willesden Junction","WJN");
                break;
            case CENTRAL:
                res.put("Bank","BNK");
                res.put("Barkingside","BDE");
                res.put("Bethnal Green","BNG");
                res.put("Bond Street","BDS");
                res.put("Buckhurst Hill","BHL");
                res.put("Chancery Lane","CYL");
                res.put("Chigwell","CHG");
                res.put("Debden","DEB");
                res.put("Ealing Broadway","EBY");
                res.put("East Acton","EAC");
                res.put("Epping","EPP");
                res.put("Fairlop","FLP");
                res.put("Gants Hill","GHL");
                res.put("Grange Hill","GRH");
                res.put("Greenford","GFD");
                res.put("Hainault","HAI");
                res.put("Hanger Lane","HLN");
                res.put("Holborn","HOL");
                res.put("Holland Park","HPK");
                res.put("Lancaster Gate","LAN");
                res.put("Leyton","LEY");
                res.put("Leytonstone","LYS");
                res.put("Liverpool Street","LST");
                res.put("Loughton","LTN");
                res.put("Marble Arch","MAR");
                res.put("Mile End","MLE");
                res.put("Newbury Park","NEP");
                res.put("North Acton","NAC");
                res.put("Northolt","NHT");
                res.put("Notting Hill Gate","NHG");
                res.put("Oxford Circus","OXC");
                res.put("Perivale","PER");
                res.put("Queensway","QWY");
                res.put("Redbridge","RED");
                res.put("Roding Valley","ROD");
                res.put("Ruislip Gardens","RUG");
                res.put("Shepherds Bush (Central Line)","SBC");
                res.put("Snaresbrook","SNB");
                res.put("South Ruislip","SRP");
                res.put("South Woodford","SWF");
                res.put("St. Paul's","STP");
                res.put("Stratford","SFD");
                res.put("Theydon Bois","THB");
                res.put("Tottenham Court Road","TCR");
                res.put("Wanstead","WAN");
                res.put("West Acton","WAC");
                res.put("West Ruislip","WRP");
                res.put("White City","WCT");
                res.put("Woodford","WFD");
                break;
            case CIRCLE:
                res.put("Aldgate","ALD");
                //res.put("Aldgate East"]="ALE");
                res.put("Baker Street","BST");
                res.put("Barbican","BAR");
                //res.put("Barking"]="BKG");
                res.put("Bayswater","BAY");
                res.put("Blackfriars","BLF");
                //res.put("Bow Road"]="BWR");
                //res.put("Bromley-by-Bow"]="BBB");
                res.put("Cannon Street","CST");
                //res.put("East Ham"]="EHM");
                res.put("Edgware Road (H & C)","ERD");
                res.put("Embankment","EMB");
                res.put("Euston Square","ESQ");
                res.put("Farringdon","FAR");
                res.put("Gloucester Road","GRD");
                res.put("Great Portland Street","GPS");
                res.put("Hammersmith (Circle and H&C)","HMS");
                res.put("High Street Kensington","HST");
                res.put("King's Cross St. Pancras","KXX");
                //res.put("Ladbroke Grove"]="LBG");
                res.put("Liverpool Street","LST");
                res.put("Mansion House","MAN");
                //res.put("Mile End"]="MLE");
                res.put("Monument","MON");
                res.put("Moorgate","MGT");
                res.put("Notting Hill Gate","NHG");
                res.put("PAddington Circle","PADc");
                res.put("PAddington H & C","PADs");
                //res.put("Plaistow"]="PLW");
                //res.put("Royal Oak"]="ROA");
                res.put("Sloane Square","SSQ");
                res.put("South Kensington","SKN");
                res.put("St. James's Park","SJP");
                //res.put("Stepney Green"]="STG");
                res.put("Temple","TEM");
                res.put("Tower Hill","THL");
                //res.put("Upton Park"]="UPK");
                res.put("Victoria","VIC");
                //res.put("West Ham"]="WHM");
                //res.put("Westbourne Park"]="WBP");
                res.put("Westminster","WMS");
                res.put("Whitechapel","WCL");
                break;
            case DISTRICT:
                res.put("Acton Town","ACT");
                res.put("Aldgate East","ALE");
                res.put("Barking","BKG");
                res.put("Barons Court","BCT");
                res.put("Becontree","BEC");
                res.put("Blackfriars","BLF");
                res.put("Bow Road","BWR");
                res.put("Bromley-by-Bow","BBB");
                res.put("Cannon Street","CST");
                res.put("Chiswick Park","CHP");
                res.put("Dagenham East","DGE");
                res.put("Dagenham Heathway","DGH");
                res.put("Ealing Broadway","EBY");
                res.put("Ealing Common","ECM");
                res.put("Earls Court","ECT");
                res.put("East Ham","EHM");
                res.put("East Putney","EPY");
                res.put("Edgware Road (H & C)","ERD");
                res.put("Elm Park","EPK");
                res.put("Embankment","EMB");
                res.put("Fulham Broadway","FBY");
                res.put("Gloucester Road","GRD");
                res.put("Gunnersbury","GUN");
                res.put("Hammersmith (District and Picc)","HMD");
                res.put("High Street Kensington","HST");
                res.put("Hornchurch","HCH");
                res.put("Kew Gardens","KEW");
                res.put("Mansion House","MAN");
                res.put("Mile End","MLE");
                res.put("Monument","MON");
                res.put("Olympia","OLY");
                res.put("Parsons Green","PGR");
                res.put("Plaistow","PLW");
                res.put("Putney Bridge","PUT");
                res.put("Ravenscourt Park","RCP");
                res.put("Richmond","RMD");
                res.put("Sloane Square","SSQ");
                res.put("Southfields","SFS");
                res.put("South Kensington","SKN");
                res.put("St. James's Park","SJP");
                res.put("Stamford Brook","STB");
                res.put("Stepney Green","STG");
                res.put("Temple","TEM");
                res.put("Tower Hill","THL");
                res.put("Turnham Green","TGR");
                res.put("Upminster","UPM");
                res.put("Upminster Bridge","UPB");
                res.put("Upney","UPY");
                res.put("Upton Park","UPK");
                res.put("Victoria","VIC");
                res.put("West Brompton","WBT");
                res.put("West Ham","WHM");
                res.put("West Kensington","WKN");
                res.put("Westminster","WMS");
                res.put("Whitechapel","WCL");
                res.put("Wimbledon","WDN");
                res.put("Wimbledon Park","WMP");
                break;
            case HAMMERSMITH:
                //res.put("Aldgate"]="ALD");
                res.put("Aldgate East","ALE");
                res.put("Baker Street","BST");
                res.put("Barbican","BAR");
                res.put("Barking","BKG");
                //res.put("Bayswater"]="BAY");
                res.put("Blackfriars","BLF");
                res.put("Bow Road","BWR");
                res.put("Bromley-by-Bow","BBB");
                //res.put("Cannon Street"]="CST");
                res.put("East Ham","EHM");
                res.put("Edgware Road (H & C)","ERD");
                //res.put("Embankment"]="EMB");
                res.put("Euston Square","ESQ");
                res.put("Farringdon","FAR");
                res.put("Great Portland Street","GPS");
                res.put("Hammersmith (Circle and H&C)","HMS");
                //res.put("High Street Kensington"]="HST");
                res.put("King's Cross St. Pancras","KXX");
                res.put("Ladbroke Grove","LBG");
                res.put("Liverpool Street","LST");
                //res.put("Mansion House"]="MAN");
                res.put("Mile End","MLE");
                //res.put("Monument"]="MON");
                res.put("Moorgate","MGT");
                //res.put("Notting Hill Gate"]="NHG");
                res.put("PAddington Circle","PADc");
                res.put("PAddington H & C","PADs");
                res.put("Plaistow","PLW");
                res.put("Royal Oak","ROA");
                //res.put("Sloane Square"]="SSQ");
                //res.put("South Kensington"]="SKN");
                //res.put("St. James's Park"]="SJP");
                res.put("Stepney Green","STG");
                //res.put("Temple"]="TEM");
                //res.put("Tower Hill"]="THL");
                res.put("Upton Park","UPK");
                //res.put("Victoria"]="VIC");
                res.put("West Ham","WHM");
                res.put("Westbourne Park","WBP");
                //res.put("Westminster"]="WMS");
                res.put("Whitechapel","WCL");
                break;
            case JUBILEE:
                res.put("Baker Street","BST");
                res.put("Bermondsey","BER");
                res.put("Bond Street","BDS");
                res.put("Canada Water","CWR");
                res.put("Canary Wharf","CWF");
                res.put("Canning Town","CNT");
                res.put("Canons Park","CPK");
                //res.put("Charing Cross"]="CHX");
                res.put("Dollis Hill","DHL");
                res.put("Finchley Road","FRD");
                res.put("Green Park","GPK");
                res.put("Kilburn","KIL");
                res.put("Kingsbury","KBY");
                res.put("London Bridge","LON");
                res.put("Neasden","NEA");
                res.put("North Greenwich","NGW");
                res.put("Queensbury","QBY");
                res.put("Southwark","SWK");
                res.put("St. John's Wood","SJW");
                res.put("Stanmore","STA");
                res.put("Stratford","SFD");
                res.put("Swiss Cottage","SWC");
                res.put("Waterloo","WLO");
                res.put("Wembley Park","WPK");
                res.put("West Ham","WHM");
                res.put("West Hampstead","WHD");
                res.put("Westminster","WMS");
                res.put("Willesden Green","WLG");
                break;
            case NORTHERN:
                res.put("Angel","ANG");
                res.put("Archway","ARC");
                res.put("Balham","BAL");
                res.put("Bank","BNK");
                res.put("Belsize Park","BPK");
                res.put("Borough","BOR");
                res.put("Brent Cross","BTX");
                res.put("Burnt Oak","BUR");
                res.put("Camden Town","CTN");
                res.put("Chalk Farm","CHF");
                res.put("Charing Cross","CHX");
                res.put("Clapham Common","CPC");
                res.put("Clapham North","CPN");
                res.put("Clapham South","CPS");
                res.put("Colindale","COL");
                res.put("Colliers Wood","CLW");
                res.put("East Finchley","EFY");
                res.put("Edgware","EDG");
                res.put("Elephant & Castle","ELE");
                res.put("Embankment","EMB");
                res.put("Euston","EUS");
                res.put("Finchley Central","FYC");
                res.put("Golders Green","GGR");
                res.put("Goodge Street","GST");
                res.put("Hampstead","HMP");
                res.put("Hendon Central","HND");
                res.put("High Barnet","HBT");
                res.put("Highgate","HIG");
                res.put("Kennington","KEN");
                res.put("Kentish Town","KTN");
                res.put("King's Cross St. Pancras","KXX");
                res.put("Leicester Square","LSQ");
                res.put("London Bridge","LON");
                res.put("Mill Hill East","MHE");
                res.put("Moorgate","MGT");
                res.put("Morden","MOR");
                res.put("Mornington C","MCR");
                res.put("Old Street","OLD");
                res.put("Oval","OVL");
                res.put("South Wimbledon","SWM");
                res.put("Stockwell","STK");
                res.put("Tooting Bec","TBE");
                res.put("Tooting Broadway","TBY");
                res.put("Tottenham Court Road","TCR");
                res.put("Totteridge and Whetstone","TOT");
                res.put("Tufnell Park","TPK");
                res.put("Warren Street","WST");
                res.put("Waterloo","WLO");
                res.put("West Finchley","WFY");
                res.put("Woodside Park","WSP");
                break;
            case VICTORIA:
                res.put("Blackhorse Road","BHR");
                res.put("Brixton","BRX");
                res.put("Euston","EUS");
                res.put("Finsbury Park","FPK");
                res.put("Green Park","GPK");
                res.put("Highbury & Islington","HBY");
                res.put("King's Cross St. Pancras","KXX");
                res.put("Oxford Circus","OXC");
                res.put("Pimlico","PIM");
                res.put("Seven Sisters","SVS");
                res.put("Stockwell","STK");
                res.put("Tottenham Hale","TTH");
                res.put("Vauxhall","VUX");
                res.put("Victoria","VIC");
                res.put("Walthamstow Central","WAL");
                res.put("Warren Street","WST");
                break;
            case WATERLOO:
                res.put("Bank","BNK");
                res.put("Waterloo","WLO");
                break;
            case METROPOLITAN:
                res.put("Aldgate","ALD");
                res.put("Amersham","AME");
                res.put("Baker Street","BST");
                res.put("Barbican","BAR");
                res.put("Chalfont & Latimer","CLF");
                res.put("Chorleywood","CWD");
                res.put("Croxley","CRX");
                res.put("Eastcote","ETE");
                res.put("Euston Square","ESQ");
                res.put("Farringdon","FAR");
                res.put("Finchley Road","FRD");
                res.put("Great Portland Street","GPS");
                res.put("Harrow on the Hill","HOH");
                res.put("Hillingdon","HDN");
                res.put("Ickenham","ICK");
                res.put("King's Cross St. Pancras","KXX");
                res.put("Liverpool Street","LST");
                res.put("Moor Park","MPK");
                res.put("Moorgate","MGT");
                res.put("North Harrow","NHR");
                res.put("Northwick Park","NWP");
                res.put("Northwood","NWD");
                res.put("Northwood Hills","NWH");
                res.put("Pinner","PIN");
                res.put("Rayners Lane","RLN");
                res.put("Rickmansworth","RKY");
                res.put("Ruislip","RUI");
                res.put("Ruislip Manor","RUM");
                res.put("Uxbridge","UXB");
                res.put("Watford","WAT");
                res.put("Wembley Park","WPK");
                res.put("West Harrow","WHR");
                res.put("Whitechapel","WCL");
                break;
        }
        return res;
    }
    static private boolean containsStation(LineType line, String station_nice)
    {
        HashMap<String, String> h = FetchStations(line);
        if (h.containsKey(station_nice)) return true;
        else return false;
    }
    static public ArrayList<LineType> FetchLinesForStation(String station_nice)
    {
        ArrayList<LineType> result = new ArrayList<LineType>();
        if (containsStation(LineType.BAKERLOO, station_nice)) result.add(LineType.BAKERLOO);
        if (containsStation(LineType.CENTRAL, station_nice)) result.add(LineType.CENTRAL);
        if (containsStation(LineType.CIRCLE, station_nice)) result.add(LineType.CIRCLE);
        if (containsStation(LineType.DISTRICT, station_nice)) result.add(LineType.DISTRICT);
        if (containsStation(LineType.DLR, station_nice)) result.add(LineType.DLR);
        if (containsStation(LineType.HAMMERSMITH, station_nice)) result.add(LineType.HAMMERSMITH);
        if (containsStation(LineType.JUBILEE, station_nice)) result.add(LineType.JUBILEE);
        if (containsStation(LineType.METROPOLITAN, station_nice)) result.add(LineType.METROPOLITAN);
        if (containsStation(LineType.NORTHERN, station_nice)) result.add(LineType.NORTHERN);
        if (containsStation(LineType.OVERGROUND, station_nice)) result.add(LineType.OVERGROUND);
        if (containsStation(LineType.PICACIDILY, station_nice)) result.add(LineType.PICACIDILY);
        if (containsStation(LineType.VICTORIA, station_nice)) result.add(LineType.VICTORIA);
        if (containsStation(LineType.WATERLOO, station_nice)) result.add(LineType.WATERLOO);
        return result;
    }
    private static String[] removeNicecess(String p)
    {
        //Make all station names that appear on StationsLocation equvalent to the representations here
        if (p.equals("Bank Monument")) {
            return new String[]{"Bank","Monument"};
        }
        else return new String[]{p};
    }

}
