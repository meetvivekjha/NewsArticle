package model;

/**
 * Created by Freeware Sys on 9/18/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class Source {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("urlsToLogos")
        @Expose
        private UrlsToLogos urlsToLogos;
        @SerializedName("sortBysAvailable")
        @Expose
        private List<String> sortBysAvailable = null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public UrlsToLogos getUrlsToLogos() {
            return urlsToLogos;
        }

        public void setUrlsToLogos(UrlsToLogos urlsToLogos) {
            this.urlsToLogos = urlsToLogos;
        }

        public List<String> getSortBysAvailable() {
            return sortBysAvailable;
        }

        public void setSortBysAvailable(List<String> sortBysAvailable) {
            this.sortBysAvailable = sortBysAvailable;
        }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Source{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", language='").append(language).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", urlsToLogos=").append(urlsToLogos);
        sb.append(", sortBysAvailable=").append(sortBysAvailable);
        sb.append('}');
        return sb.toString();
    }

}


