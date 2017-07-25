package cn.ogsu.vod.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.ogsu.vod.service.ISongService;
import cn.ogsu.vod.util.Page;
import cn.ogsu.vod.util.PageData;
/**
 * 歌曲管理的service实现层
 * @author albert
 * @time 2016年9月10日
 */
@Service
public   class SongServiceImpl extends ServiceBase implements ISongService{
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> songList(Page page) throws Exception {
		List<PageData> songs=(List<PageData>) daoSupport.findForList("SongMapper.listPageSong",page);
		PageData song=null;
		for (int i = 0; i <songs.size(); i++) {
			song=songs.get(i);
			String song_format=song.get("song_format").toString();
			if(song_format.equals("0")){
				song.put("song_format", "VOD");
			}else if(song_format.equals("1")){
				song.put("song_format", "MRS");
			}else{
				song.put("song_format", "MIDI");
			}
			String nature=song.get("nature").toString();
			if(nature.equals("0")){
				song.put("nature","原版");
			}else if(nature.equals("1")){
				song.put("nature","翻唱");
			}else if(nature.equals("2")){
				song.put("nature","原版LIVE");
			}else if(nature.equals("3")){
				song.put("nature","翻唱LIVE");
			}else{
				song.put("nature","");
			}
			String scene=song.get("scene").toString();
			if(scene.equals("0")){
				song.put("scene","LIVE");
			}else if(scene.equals("1")){
				song.put("scene","MTV");
			}else if(scene.equals("2")){
				song.put("scene","非原画");
			}else{
				song.put("scene","");
			}
			String pixel=song.get("pixel").toString();
			if(pixel.equals("0")){
				song.put("pixel","480p");
			}else if(pixel.equals("1")){
				song.put("pixel","720p");
			}else if(pixel.equals("2")){
				song.put("pixel","1080p");
			}else if(pixel.equals("3")){
				song.put("pixel","1080p以上");
			}else{
				song.put("pixel","");
			}
			String song_years=song.get("song_years").toString();
			if(song_years.equals("0")){
				song.put("song_years", "70's");
			}else if(song_years.equals("1")){
				song.put("song_years", "80's");
			}else if(song_years.equals("2")){
				song.put("song_years", "90's");
			}else if(song_years.equals("3")){
				song.put("song_years", "00's");
			}else if(song_years.equals("4")){
				song.put("song_years", "00's以后");
			}else if(song_years.equals("5")){
				song.put("song_years", "70's以前");
			}else{
				song.put("song_years", "");
			}
			String singer=song.get("singerOneName").toString();
			if(song.get("singerTwoName")!=null){
				singer+="/"+song.get("singerTwoName");
			}
			if(song.get("singerThreeName")!=null){
				singer+="/"+song.get("singerThreeName");
			}
			song.remove("singerOneName");
			song.put("singer", singer);
			String singerNo=song.get("singer_id_one").toString();
			if(!song.get("singer_id_two").toString().equals("-1")){
				singerNo+=song.get("singer_id_two");
			}
			if(!song.get("singer_id_three").toString().equals("-1")){
				singerNo+=song.get("singer_id_three");
			}
			song.remove("singer_id_one");
			song.put("singerNo", singerNo);
		}
		return songs;
	}
	@Override
	public PageData findSingleSong(String songId) throws Exception {
		return (PageData) daoSupport.findForObject("SongMapper.singleSong", songId);
	}
	@Override
	public boolean addSong(PageData pd) throws Exception {
		return ((int)daoSupport.save("SongMapper.addSong", pd))>0;
	}
	@Override
	public boolean updateSong(PageData pd) throws Exception {
		pd.put("status","0");
		return ((int)daoSupport.update("SongMapper.updateSong", pd))>0;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> rankList(Page page) throws Exception {
		List<PageData> data=(List<PageData>) daoSupport.findForList("SongMapper.listPageRank", page);
		int currentPage=page.getCurrentPage();
		int showCount=page.getShowCount();
		int rank=(currentPage-1)*showCount;
		for (int i = 0; i <data.size(); i++) {
			data.get(i).put("rank",(rank+1));
			rank++;
		}
		return data;
	}
	@Override
	public boolean deleteSong(PageData pd) throws Exception {
		int count=(int)daoSupport.delete("SongMapper.deleteSongs", pd);
		return count>0;
	}
	@Override
	public boolean updateStatus(PageData pd) throws Exception {
		return ((int)daoSupport.update("SongMapper.updateStatus", pd))>0;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<String> autoSearch(PageData pd) throws Exception {
		return (List<String>) daoSupport.findForList("SongMapper.autoSongName", pd);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainAllData() throws Exception {
		return (List<PageData>) daoSupport.findForList("SongMapper.findAllSong",null);
	}
	@Override
	public boolean addImportSongs(List<PageData> pds) throws Exception {
		List<PageData> pageSongs=null;
		int count=0;
		while(pds.size()!=0){
			//分批次插入数据
			pageSongs=calcPageData(pds);
			count+=(int)daoSupport.save("SongMapper.importSongs",pageSongs);
		}
		return count>0;
	}
	/**
	 * 只从list中获取最多5条数据
	 * @param songs
	 * @return
	 */
	private List<PageData> calcPageData(List<PageData> songs){
		List<PageData> pageSongs=new ArrayList<>();
		for (int i = 0; i<songs.size(); i++) {
			if(i==5) break;
			PageData pd=songs.get(i);
			pageSongs.add(pd);
			songs.remove(i);
		}
		return pageSongs;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<PageData> obtainTemplateData() throws Exception {
		return (List<PageData>) daoSupport.findForList("SongMapper.findTemplateData",null);
	}
	@Override
	public boolean updateSongInfo(PageData pd) throws Exception {
		return ((int)daoSupport.update("SongMapper.updateSongById", pd))>0;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<String> findIdByName(String songName) throws Exception {
		PageData pd=new PageData();
		pd.put("songName",songName);
		return (List<String>) daoSupport.findForList("SongMapper.findIdByName", pd);
	}
}