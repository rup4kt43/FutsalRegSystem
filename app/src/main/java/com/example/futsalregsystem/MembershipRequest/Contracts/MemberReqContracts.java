package com.example.futsalregsystem.MembershipRequest.Contracts;

import com.example.futsalregsystem.MembershipRequest.DTO.MemberReqDTO;

import java.util.ArrayList;

public interface MemberReqContracts {

    interface  view{
        void showToast(String msg);
        void loadAdapter(ArrayList<MemberReqDTO> details);

    }
     interface presenter{
        void loadRequest();
     }

     interface modelFirebaseCallback{
        void loadDetails(ArrayList<MemberReqDTO> details);
     }

     interface  presenterModelCallback{
         void loadDetails(ArrayList<MemberReqDTO> details);
     }
}
