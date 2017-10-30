package com.app.devjo.marvelcomics.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by kjoha on 17/3/2017.
 *
 * Layer level 3 call api rest comics
 */

public class Comic {

    public int id;
    public String title;
    public List<Image> images;
    public List<ComicPrice> prices;
    public String description;
    public List<ComicDate> dates;
    public int pageCount;
    public SeriesSummary series;
    public CreatorList creators;
    public CharacterList characters;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<ComicPrice> getPrices() {
        return prices;
    }

    public String getDescription() {
        return description;
    }

    public List<ComicDate> getDates() {
        return dates;
    }

    public int getPageCount() {
        return pageCount;
    }

    public SeriesSummary getSeries() {
        return series;
    }

    public CreatorList getCreators() {
        return creators;
    }

    public CharacterList getCharacters() {
        return characters;
    }

   public class Image{
        public String path;
        public String extension;

        public String getPath() {
            return path;
        }

        public String getExtension() {
            return extension;
        }
    }

    public class ComicPrice{
        public String type;
        public float price;

        public String getType() {
            return type;
        }

        public float getPrice() {
            return price;
        }
    }

    public class ComicDate{
        public String type;
        public Date date;

        public String getType() {
            return type;
        }

        public Date getDate() {
            return date;
        }
    }

    public class SeriesSummary{
        public String resourceURI;
        public String name;

        public String getResourceURI() {
            return resourceURI;
        }

        public String getName() {
            return name;
        }
    }

    public class CharacterList{
        public int available;
        public int returned;
        public String collectionURI;
        public List<CharacterSummary> items;

        public int getAvailable() {
            return available;
        }

        public int getReturned() {
            return returned;
        }

        public String getCollectionURI() {
            return collectionURI;
        }

        public List<CharacterSummary> getItems() {
            return items;
        }
    }

    public class CharacterSummary{
        public String resourceURI;
        public String name;
        public String role;

        public String getResourceURI() {
            return resourceURI;
        }

        public String getName() {
            return name;
        }

        public String getRole() {
            return role;
        }
    }

    public class CreatorList{
        public int available;
        public int returned;
        public String collectionURI;
        public List<CreatorSummary> items;

        public int getAvailable() {
            return available;
        }

        public int getReturned() {
            return returned;
        }

        public String getCollectionURI() {
            return collectionURI;
        }

        public List<CreatorSummary> getItems() {
            return items;
        }
    }

    public class CreatorSummary{
        public String resourceURI;
        public String name;
        public String role;

        public String getResourceURI() {
            return resourceURI;
        }

        public String getName() {
            return name;
        }

        public String getRole() {
            return role;
        }
    }
}