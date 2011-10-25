package cz.zcu.kiv.eegdatabase.data.pojo;
// Generated 19.1.2010 23:18:53 by Hibernate Tools 3.2.1.GA

import org.hibernate.annotations.Entity;
import org.hibernate.search.annotations.*;

import java.io.Serializable;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

/**
 * DataFile generated by hbm2java
 */
@Entity
@Indexed
public class DataFile implements Serializable {

    @DocumentId
    private int dataFileId;
    private Experiment experiment;
    private double samplingRate;
    private Blob fileContent;
    @Fields({
            @Field(index = Index.TOKENIZED), //same property indexed multiple times
            @Field(name = "mimetype")}) //use a different field name
    private String mimetype;
    @Fields({
            @Field(index = Index.TOKENIZED), //same property indexed multiple times
            @Field(name = "filename")}) //use a different field name
    private String filename;
    private Set<FileMetadataParamVal> fileMetadataParamVals = new HashSet<FileMetadataParamVal>(0);
    private Set<History> histories = new HashSet<History>(0);
    private long scn;

    public DataFile() {
    }

    public DataFile(int dataFileId, Experiment experiment, double samplingRate, Blob fileContent, String mimetype, String filename) {
        this.dataFileId = dataFileId;
        this.experiment = experiment;
        this.samplingRate = samplingRate;
        this.fileContent = fileContent;
        this.mimetype = mimetype;
        this.filename = filename;
    }

    public DataFile(int dataFileId, Experiment experiment, double samplingRate, Blob fileContent, String mimetype, String filename, Set<FileMetadataParamVal> fileMetadataParamVals) {
        this.dataFileId = dataFileId;
        this.experiment = experiment;
        this.samplingRate = samplingRate;
        this.fileContent = fileContent;
        this.mimetype = mimetype;
        this.filename = filename;
        this.fileMetadataParamVals = fileMetadataParamVals;
    }

    public int getDataFileId() {
        return this.dataFileId;
    }

    public void setDataFileId(int dataFileId) {
        this.dataFileId = dataFileId;
    }

    public Experiment getExperiment() {
        return this.experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public double getSamplingRate() {
        return this.samplingRate;
    }

    public void setSamplingRate(double samplingRate) {
        this.samplingRate = samplingRate;
    }

    public Blob getFileContent() {
        return this.fileContent;
    }

    public void setFileContent(Blob fileContent) {
        this.fileContent = fileContent;
    }

    public String getMimetype() {
        return this.mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getScn() {
        return scn;
    }

    public void setScn(long scn) {
        this.scn = scn;
    }

    public Set<FileMetadataParamVal> getFileMetadataParamVals() {
        return this.fileMetadataParamVals;
    }

    public void setFileMetadataParamVals(Set<FileMetadataParamVal> fileMetadataParamVals) {
        this.fileMetadataParamVals = fileMetadataParamVals;
    }

    public Set<History> getHistories() {
        return histories;
    }

    public void setHistories(Set<History> histories) {
        this.histories = histories;
    }
}


