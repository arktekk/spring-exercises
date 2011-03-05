package no.arktekk.training.spring.form;

import com.google.common.base.Function;
import no.arktekk.training.spring.domain.Album;
import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.domain.Track;

import static com.google.common.collect.Lists.transform;

/**
 * A collection of domain mapping transformation functions.
 * Each function has the responisibility of conveting between the form representation
 * and the domain object representation.
 * <p/>
 * Would be nice if we had first class functions in Java, but while we are waiting
 * google guava is a substitute.
 *
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Transformations {

    public static Function<? super AlbumForm, ? extends Album> asAlbum = new Function<AlbumForm, Album>() {
        public Album apply(AlbumForm form) {
            return new Album(form.getId(), form.getTitle(), form.getArtist(), form.getCategory(), form.getLabel(), transform(form.getTracks(), asTrack));
        }
    };

    public static Function<? super Album, ? extends AlbumForm> asAlbumForm = new Function<Album, AlbumForm>() {
        public AlbumForm apply(Album album) {
            AlbumForm form = new AlbumForm();
            form.setArtist(album.artist());
            form.setCategory(album.category());
            form.setLabel(album.label());
            form.setTracks(transform(album.tracks(), asTrackForm));
            form.setTitle(album.title());
            return form;
        }
    };

    public static Function<? super Auction, ? extends AuctionForm> asAuctionForm = new Function<Auction, AuctionForm>() {
        public AuctionForm apply(Auction auction) {
            AuctionForm form = new AuctionForm();
            form.setAlbums(transform(auction.albums(), asAlbumForm));
            form.setDescription(auction.description());
            form.setExpiresDate(auction.expires());
            form.setId(auction.id());
            form.setMinimumPrice(auction.minimumPrice());
            form.setStartDate(auction.starts());
            return form;
        }
    };

    public static Function<? super Track, ? extends TrackForm> asTrackForm = new Function<Track, TrackForm>() {
        public TrackForm apply(Track track) {
            TrackForm form = new TrackForm();
            form.setId(track.id());
            form.setDuration(track.duration());
            form.setLive(track.live());
            form.setName(track.name());
            form.setNote(track.note());
            form.setNumber(track.number());
            return form;
        }
    };


    public static Function<? super TrackForm, ? extends Track> asTrack = new Function<TrackForm, Track>() {
        public Track apply(TrackForm form) {
            Track track = new Track(
                    form.getId(),
                    form.getName(),
                    form.getNumber(),
                    form.getDuration(),
                    form.isLive(),
                    form.getNote()
            );

            return track;
        }
    };
}
