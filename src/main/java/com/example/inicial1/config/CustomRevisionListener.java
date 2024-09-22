package com.example.inicial1.config;

public class CustomRevisionListener implements ReevisionListener{
    public void newRevision (Object revisionEntity) {final Revision revision =(Revision) revisionEntity;}
}
