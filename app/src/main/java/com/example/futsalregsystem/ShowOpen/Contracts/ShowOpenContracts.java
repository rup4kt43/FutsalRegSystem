package com.example.futsalregsystem.ShowOpen.Contracts;

import com.example.futsalregsystem.ShowOpen.DTO.ShowOpenDTO;

import java.util.ArrayList;

public interface ShowOpenContracts {

    interface view {
        void loadOpenList(ArrayList<ShowOpenDTO> openList);

    }

    interface presenter {
        void loadOpenSchedule();

    }

    interface modelFirebaseCallBack {

        void openList(ArrayList<ShowOpenDTO> openList);

    }

    interface presenterModelCallback {
        void openList(ArrayList<ShowOpenDTO> openList);
    }
}
